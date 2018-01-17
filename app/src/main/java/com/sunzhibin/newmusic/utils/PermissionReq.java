package com.sunzhibin.newmusic.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Android运行时权限申请
 * <p>
 * 需要申请的权限列表，<a "href=https://developer.android.google.cn/guide/topics/security/permissions.html?hl=zh-cn#normal-dangerous">Google Doc</a>
 * <p>
 * -CALENDAR<br>
 * {@link android.Manifest.permission#READ_CALENDAR}<br>
 * {@link android.Manifest.permission#WRITE_CALENDAR}<br>
 * <p>
 * -CAMERA<br>
 * {@link android.Manifest.permission#CAMERA}<br>
 * <p>
 * -CONTACTS<br>
 * {@link android.Manifest.permission#READ_CONTACTS}<br>
 * {@link android.Manifest.permission#WRITE_CONTACTS}<br>
 * {@link android.Manifest.permission#GET_ACCOUNTS}<br>
 * <p>
 * -LOCATION<br>
 * {@link android.Manifest.permission#ACCESS_FINE_LOCATION}<br>
 * {@link android.Manifest.permission#ACCESS_COARSE_LOCATION}<br>
 * <p>
 * -MICROPHONE<br>
 * {@link android.Manifest.permission#RECORD_AUDIO}<br>
 * <p>
 * -PHONE<br>
 * {@link android.Manifest.permission#READ_PHONE_STATE}<br>
 * {@link android.Manifest.permission#CALL_PHONE}<br>
 * {@link android.Manifest.permission#READ_CALL_LOG}<br>
 * {@link android.Manifest.permission#WRITE_CALL_LOG}<br>
 * {@link android.Manifest.permission#ADD_VOICEMAIL}<br>
 * {@link android.Manifest.permission#USE_SIP}<br>
 * {@link android.Manifest.permission#PROCESS_OUTGOING_CALLS}<br>
 * <p>
 * -SENSORS<br>
 * {@link android.Manifest.permission#BODY_SENSORS}<br>
 * <p>
 * -SMS<br>
 * {@link android.Manifest.permission#SEND_SMS}<br>
 * {@link android.Manifest.permission#RECEIVE_SMS}<br>
 * {@link android.Manifest.permission#READ_SMS}<br>
 * {@link android.Manifest.permission#RECEIVE_WAP_PUSH}<br>
 * {@link android.Manifest.permission#RECEIVE_MMS}<br>
 * <p>
 * -STORAGE<br>
 * {@link android.Manifest.permission#READ_EXTERNAL_STORAGE}<br>
 * {@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}<br>
 */
public class PermissionReq {
    private static AtomicInteger sRequestCode = new AtomicInteger(0);
    private static SparseArray<Result> sResultArray = new SparseArray<>();
    private static Set<String> sManifestPermissionSet;

    public interface Result {
        void onGranted();

        void onDenied();
    }

    private Object mObject;
    private String[] mPermissions;
    private Result mResult;

    private PermissionReq(Object object) {
        mObject = object;
    }

    public static PermissionReq with(@NonNull Activity activity) {
        return new PermissionReq(activity);
    }

    public static PermissionReq with(@NonNull Fragment fragment) {
        return new PermissionReq(fragment);
    }

    public PermissionReq permissions(@NonNull String... permissions) {
        mPermissions = permissions;
        return this;
    }

    public PermissionReq result(@Nullable Result result) {
        mResult = result;
        return this;
    }

    public void request() {
        Activity activity = getActivity(mObject);
        if (activity == null) {
            throw new IllegalArgumentException(mObject.getClass().getName() + " is not supported");
        }
        initManifestPermission(activity);
        for (String permission : mPermissions) {
            if (!sManifestPermissionSet.contains(permission)) {
                if (mResult != null) {
                    mResult.onDenied();
                }
                return;
            }
        }
        if (hasPermission(activity, mPermissions)) {
            if (mResult != null) {
                mResult.onGranted();
            }
            return;
        }
        List<String> deniedPermissionList = getDeniedPermissions(activity, mPermissions);
        if (deniedPermissionList.isEmpty()) {
            if (mResult != null) {
                mResult.onGranted();
            }
            return;
        }

        int requestCode = genRequestCode();
        String[] deniedPermissions = deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
        requestPermissions(mObject, deniedPermissions, requestCode);
        sResultArray.put(requestCode, mResult);
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Result result = sResultArray.get(requestCode);

        if (result == null) {
            return;
        }
        sResultArray.remove(requestCode);
        if (permissions.length == 0 && grantResults.length == 0) {
            //未授权
            result.onDenied();

        } else {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    result.onDenied();
                    return;
                }
            }
            result.onGranted();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void requestPermissions(Object object, String[] permissions, int requestCode) {
        if (object instanceof Activity) {
            ((Activity) object).requestPermissions(permissions, requestCode);
        } else if (object instanceof Fragment) {
            ((Fragment) object).requestPermissions(permissions, requestCode);
        }
    }

    private static List<String> getDeniedPermissions(Context context, String[] permissions) {
        List<String> deniedPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissionList.add(permission);
            }
        }
        return deniedPermissionList;
    }

    private static synchronized void initManifestPermission(Context context) {
        if (sManifestPermissionSet == null) {
            sManifestPermissionSet = new HashSet<>();
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
                String[] permissions = packageInfo.requestedPermissions;
                Collections.addAll(sManifestPermissionSet, permissions);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static Activity getActivity(Object object) {
        if (object != null) {
            if (object instanceof Activity) {
                return (Activity) object;
            } else if (object instanceof Fragment) {
                return ((Fragment) object).getActivity();
            }
        }
        return null;
    }

    private static int genRequestCode() {
        return sRequestCode.incrementAndGet();
    }


    /**
     * 系统层的权限判断
     *
     * @param context     上下文
     * @param permissions 申请的权限 Manifest.permission.READ_CONTACTS
     * @return 是否有权限 ：其中有一个获取不了就是失败了
     */
    private static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true;
        for (String permission : permissions) {
            String op = AppOpsManagerCompat.permissionToOp(permission);
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false;
            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    private static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(permissions));
        return hasPermission(context, arrayList);
    }
}
