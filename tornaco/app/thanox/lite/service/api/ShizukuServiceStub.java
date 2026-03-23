package tornaco.app.thanox.lite.service.api;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.request.target.Target;
import github.tornaco.android.thanos.BuildProp;
import github.tornaco.android.thanos.core.DoNotStrip;
import github.tornaco.android.thanos.core.IApp;
import github.tornaco.android.thanos.core.ICallback;
import github.tornaco.android.thanos.core.IPkgChangeListener;
import github.tornaco.android.thanos.core.IThanosLite;
import github.tornaco.android.thanos.core.Logger;
import github.tornaco.android.thanos.core.LoggerKt;
import github.tornaco.android.thanos.core.ServicesKt;
import github.tornaco.android.thanos.core.T;
import github.tornaco.android.thanos.core.app.RunningAppProcessInfoCompat;
import github.tornaco.android.thanos.core.app.usage.ProcessCpuUsageStats;
import github.tornaco.android.thanos.core.compat.NotificationCompat;
import github.tornaco.android.thanos.core.net.TrafficStats;
import github.tornaco.android.thanos.core.os.Methods;
import github.tornaco.android.thanos.core.os.ProcessName;
import github.tornaco.android.thanos.core.os.SwapInfo;
import github.tornaco.android.thanos.core.pm.AppInfo;
import github.tornaco.android.thanos.core.pm.ComponentInfo;
import github.tornaco.android.thanos.core.pm.Pkg;
import github.tornaco.android.thanos.core.process.ProcessRecord;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import lyiahf.vczjk.d03;
import lyiahf.vczjk.dz0;
import lyiahf.vczjk.e86;
import lyiahf.vczjk.eu0;
import lyiahf.vczjk.f55;
import lyiahf.vczjk.jp8;
import lyiahf.vczjk.kp4;
import lyiahf.vczjk.lr;
import lyiahf.vczjk.o0OO0O0;
import lyiahf.vczjk.p35;
import lyiahf.vczjk.q93;
import lyiahf.vczjk.qm8;
import lyiahf.vczjk.r47;
import lyiahf.vczjk.r92;
import lyiahf.vczjk.rl6;
import lyiahf.vczjk.sy;
import lyiahf.vczjk.tp6;
import lyiahf.vczjk.up9;
import lyiahf.vczjk.vp9;
import lyiahf.vczjk.vs7;
import lyiahf.vczjk.xp9;
import lyiahf.vczjk.xr6;
import lyiahf.vczjk.yp9;
import lyiahf.vczjk.z8a;
import lyiahf.vczjk.zsa;
import lyiahf.vczjk.zy2;
@DoNotStrip
@Metadata(d1 = {"\u0000ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u008c\u00012\u00020\u0001:\u0002\u008d\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ9\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020\u0010H\u0016¢\u0006\u0004\b$\u0010%J!\u0010)\u001a\u0004\u0018\u00010(2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\nH\u0016¢\u0006\u0004\b)\u0010*J!\u0010,\u001a\u0004\u0018\u00010(2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\nH\u0016¢\u0006\u0004\b,\u0010*J\u0017\u0010-\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u0002022\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00108\u001a\u0002072\u0006\u00106\u001a\u000205H\u0016¢\u0006\u0004\b8\u00109J\u0019\u0010<\u001a\u00020\u00042\b\u0010;\u001a\u0004\u0018\u00010:H\u0016¢\u0006\u0004\b<\u0010=J/\u0010>\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b>\u0010?J\u0015\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001f0\fH\u0016¢\u0006\u0004\b@\u0010AJ\u001f\u0010B\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\nH\u0016¢\u0006\u0004\bB\u0010CJ%\u0010F\u001a\u00020\u00042\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010E\u001a\u000205H\u0016¢\u0006\u0004\bF\u0010GJ\u0017\u0010J\u001a\u00020\n2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bJ\u0010KJ!\u0010N\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u001f2\b\u0010M\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\bN\u0010OJ%\u0010P\u001a\u00020\u00042\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010M\u001a\u00020\u0006H\u0016¢\u0006\u0004\bP\u0010QJ\u0017\u0010S\u001a\u0002052\u0006\u0010R\u001a\u00020:H\u0016¢\u0006\u0004\bS\u0010TJ\u0017\u0010U\u001a\u00020\n2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bU\u0010KJ\u0017\u0010X\u001a\u00020\u00042\u0006\u0010W\u001a\u00020VH\u0016¢\u0006\u0004\bX\u0010YJ\u0019\u0010\\\u001a\u00020\u00042\b\u0010[\u001a\u0004\u0018\u00010ZH\u0016¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\u00042\b\u0010[\u001a\u0004\u0018\u00010ZH\u0016¢\u0006\u0004\b^\u0010]J/\u0010_\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b_\u0010?J\u001d\u0010`\u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b`\u0010!J1\u0010e\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\n2\b\u0010b\u001a\u0004\u0018\u00010a2\u0006\u0010c\u001a\u00020\n2\u0006\u0010d\u001a\u00020\nH\u0016¢\u0006\u0004\be\u0010fJ\u001f\u0010g\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010b\u001a\u00020aH\u0016¢\u0006\u0004\bg\u0010hJ'\u0010k\u001a\b\u0012\u0004\u0012\u00020j0\f2\u0006\u0010'\u001a\u00020\n2\b\u0010i\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\bk\u0010lJ7\u0010o\u001a\b\u0012\u0004\u0012\u00020j0\f2\u0006\u0010'\u001a\u00020\n2\b\u0010i\u001a\u0004\u0018\u00010\u00062\u0006\u0010m\u001a\u00020\n2\u0006\u0010n\u001a\u00020\nH\u0016¢\u0006\u0004\bo\u0010pJ\u0017\u0010s\u001a\u00020\u00042\u0006\u0010r\u001a\u00020qH\u0016¢\u0006\u0004\bs\u0010tJ\u0017\u0010v\u001a\u00020u2\u0006\u0010+\u001a\u00020\nH\u0016¢\u0006\u0004\bv\u0010wJ\u000f\u0010x\u001a\u00020\u0004H\u0016¢\u0006\u0004\bx\u0010\u0003J%\u0010z\u001a\b\u0012\u0004\u0012\u00020y0\f2\u0006\u0010\"\u001a\u00020#2\u0006\u00106\u001a\u000205H\u0016¢\u0006\u0004\bz\u0010{J\u001f\u0010|\u001a\u0002072\u0006\u0010\"\u001a\u00020#2\u0006\u00106\u001a\u000205H\u0016¢\u0006\u0004\b|\u0010}J\"\u0010\u007f\u001a\u0004\u0018\u00010~2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\nH\u0016¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0011\u0010\u0081\u0001\u001a\u00020\u0004H\u0002¢\u0006\u0005\b\u0081\u0001\u0010\u0003J\u0011\u0010\u0082\u0001\u001a\u00020\u0004H\u0002¢\u0006\u0005\b\u0082\u0001\u0010\u0003R\u0018\u0010\u0084\u0001\u001a\u00030\u0083\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R!\u0010\u008b\u0001\u001a\u00030\u0086\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0087\u0001\u0010\u0088\u0001\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001¨\u0006\u008e\u0001"}, d2 = {"Ltornaco/app/thanox/lite/service/api/ShizukuServiceStub;", "Lgithub/tornaco/android/thanos/core/IThanosLite$Stub;", "<init>", "()V", "Llyiahf/vczjk/z8a;", "destroy", "", "fingerPrint", "()Ljava/lang/String;", "getVersionName", "", "max", "", "Landroid/app/ActivityManager$RunningServiceInfo;", "getRunningServiceLegacy", "(I)Ljava/util/List;", "", "filterAppFlags", "code", "deviceId", "Lgithub/tornaco/android/thanos/core/ICallback;", "callback", "Lgithub/tornaco/android/thanos/core/app/RunningAppProcessInfoCompat;", "getRunningAppProcessLegacy", "([ILjava/lang/String;Ljava/lang/String;Lgithub/tornaco/android/thanos/core/ICallback;)Ljava/util/List;", "", "data", "", "Lgithub/tornaco/android/thanos/core/process/ProcessRecord;", "getRunningAppProcess", "([B)[Lgithub/tornaco/android/thanos/core/process/ProcessRecord;", "Lgithub/tornaco/android/thanos/core/pm/Pkg;", "getRunningAppPackages", "([B)Ljava/util/List;", "pids", "", "getProcessPss", "([I)[J", "pkgName", "userId", "Lgithub/tornaco/android/thanos/core/pm/AppInfo;", "getAppInfoForUser", "(Ljava/lang/String;I)Lgithub/tornaco/android/thanos/core/pm/AppInfo;", "uid", "getAppInfoForUid", "getRunningAppsCount", "([B)I", "Landroid/app/ActivityManager$MemoryInfo;", "getMemoryInfo", "([B)Landroid/app/ActivityManager$MemoryInfo;", "Lgithub/tornaco/android/thanos/core/os/SwapInfo;", "getSwapInfo", "([B)Lgithub/tornaco/android/thanos/core/os/SwapInfo;", "", "update", "", "getTotalCpuPercent", "(Z)F", "Landroid/content/Intent;", "action", "handleBroadcast", "(Landroid/content/Intent;)V", "syncSFSettings", "([BLjava/lang/String;Ljava/lang/String;Lgithub/tornaco/android/thanos/core/ICallback;)V", "getSFUnSelectedPkgs", "()Ljava/util/List;", "launchFreezedAppForUser", "(Ljava/lang/String;I)V", "pkgs", "freeze", "freezePkgs", "(Ljava/util/List;Z)V", "Lgithub/tornaco/android/thanos/core/os/ProcessName;", "processName", "killProcessByName", "(Lgithub/tornaco/android/thanos/core/os/ProcessName;)I", T.Actions.ACTION_LOCKER_VERIFY_EXTRA_PACKAGE, "reason", "forceStopPackage", "(Lgithub/tornaco/android/thanos/core/pm/Pkg;Ljava/lang/String;)V", "forceStopPackages", "(Ljava/util/List;Ljava/lang/String;)V", "intent", "stopService", "(Landroid/content/Intent;)Z", "getPid", "Lgithub/tornaco/android/thanos/core/IApp;", "binder", "attachAppBinder", "(Lgithub/tornaco/android/thanos/core/IApp;)V", "Lgithub/tornaco/android/thanos/core/IPkgChangeListener;", "listener", "registerPkgStateChangeListener", "(Lgithub/tornaco/android/thanos/core/IPkgChangeListener;)V", "unregisterPkgStateChangeListener", "syncBCSettings", "getAllPkgs", "Landroid/content/ComponentName;", "componentName", "newState", "flags", "setComponentEnabledSetting", "(ILandroid/content/ComponentName;II)V", "getComponentEnabledSetting", "(ILandroid/content/ComponentName;)I", "packageName", "Lgithub/tornaco/android/thanos/core/pm/ComponentInfo;", "getActivities", "(ILjava/lang/String;)Ljava/util/List;", "itemCountInEachBatch", "batchIndex", "getActivitiesInBatch", "(ILjava/lang/String;II)Ljava/util/List;", "Landroid/os/ParcelFileDescriptor;", "pfd", "writeLogsTo", "(Landroid/os/ParcelFileDescriptor;)V", "Lgithub/tornaco/android/thanos/core/net/TrafficStats;", "getUidTrafficStats", "(I)Lgithub/tornaco/android/thanos/core/net/TrafficStats;", "updateProcessCpuUsageStats", "Lgithub/tornaco/android/thanos/core/app/usage/ProcessCpuUsageStats;", "queryProcessCpuUsageStats", "([JZ)Ljava/util/List;", "queryCpuUsageRatio", "([JZ)F", "Landroid/graphics/Bitmap;", "getAppIcon", "(Ljava/lang/String;I)Landroid/graphics/Bitmap;", "initLog", "appendDebugLogHeader", "Lgithub/tornaco/android/thanos/core/Logger;", "logger", "Lgithub/tornaco/android/thanos/core/Logger;", "Llyiahf/vczjk/yp9;", "service$delegate", "Llyiahf/vczjk/kp4;", Methods.getService, "()Llyiahf/vczjk/yp9;", NotificationCompat.CATEGORY_SERVICE, "Companion", "lyiahf/vczjk/qm8", "services"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ShizukuServiceStub extends IThanosLite.Stub {
    public static final qm8 Companion = new Object();
    private static final File logDir;
    private static final File serverDir;
    private final Logger logger = new Logger("ThanoxServiceStub");
    private final kp4 service$delegate = jp8.Oooo0(new p35(27));

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, lyiahf.vczjk.qm8] */
    static {
        File file = new File("/data/local/tmp/thanox_lite");
        serverDir = file;
        logDir = new File(file, "log");
    }

    public ShizukuServiceStub() {
        initLog();
        Objects.toString(ActivityThread.currentActivityThread());
        Objects.toString(ActivityThread.currentApplication());
        if (xr6.OooO0Oo == null) {
            try {
                Context context = (Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication", null).invoke(null, null);
                while (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                xr6.OooO0Oo = context;
            } catch (Exception unused) {
            }
        }
        Context context2 = xr6.OooO0Oo;
        yp9 service = getService();
        v34.OooOo0o(context2);
        service.getClass();
        service.OooO = context2;
        service.OooO0oo = true;
        Logger OooO0oO = service.OooO0oO();
        String name = context2.getClass().getName();
        OooO0oO.i("start: Service with context: " + context2 + " - " + name);
        yp9 service2 = getService();
        Logger OooO0oO2 = service2.OooO0oO();
        OooO0oO2.i("systemReady: " + service2);
        service2.OooOOo0();
        r92 r92Var = (r92) service2.OooOOO.getValue();
        r92Var.OooO0Oo = ServicesKt.getPowerManager(r92Var.OooO00o).isInteractive();
        r92Var.OooO0O0 = true;
        tp6.OooOooo(tp6.OooOo(service2), new up9(service2, null));
        tp6.OooOooo(tp6.OooOo(service2), new vp9(service2, null));
        tp6.OooOooo(service2.OooOOOO, new xp9(service2, null));
    }

    private final void appendDebugLogHeader() {
        this.logger.w(System.lineSeparator());
        this.logger.w(System.lineSeparator());
        this.logger.w("-------------------");
        this.logger.w(System.lineSeparator());
        Logger logger = this.logger;
        String str = Build.DEVICE;
        logger.w("DEVICE: " + str);
        this.logger.w(System.lineSeparator());
        Logger logger2 = this.logger;
        String str2 = Build.MANUFACTURER;
        logger2.w("MANUFACTURER: " + str2);
        this.logger.w(System.lineSeparator());
        Logger logger3 = this.logger;
        String str3 = Build.ID;
        logger3.w("ID: " + str3);
        this.logger.w(System.lineSeparator());
        Logger logger4 = this.logger;
        String str4 = Build.BRAND;
        logger4.w("BRAND: " + str4);
        this.logger.w(System.lineSeparator());
        Logger logger5 = this.logger;
        String str5 = Build.MODEL;
        logger5.w("MODEL: " + str5);
        this.logger.w(System.lineSeparator());
        Logger logger6 = this.logger;
        int i = Build.VERSION.SDK_INT;
        logger6.w("SDK: " + i);
        this.logger.w(System.lineSeparator());
        this.logger.w("THANOX: 8.6 3354368");
        this.logger.w(System.lineSeparator());
        this.logger.w("-------------------");
        this.logger.w(System.lineSeparator());
        this.logger.w(System.lineSeparator());
    }

    private final yp9 getService() {
        return (yp9) this.service$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initLog() {
        Object OooO0oo;
        File file = logDir;
        file.mkdirs();
        Object obj = new Object();
        File file2 = new File(file, "write_test.txt");
        try {
            String str = "hello from File API at " + System.currentTimeMillis() + "\n";
            Charset charset = eu0.OooO00o;
            v34.OooOoO(str, "text");
            v34.OooOoO(charset, "charset");
            FileOutputStream fileOutputStream = new FileOutputStream(file2, true);
            d03.o00Ooo(fileOutputStream, str, charset);
            fileOutputStream.close();
            OooO0oo = Boolean.TRUE;
        } catch (Throwable th) {
            OooO0oo = rl6.OooO0oo(th);
        }
        if (vs7.OooO00o(OooO0oo) != null) {
            OooO0oo = Boolean.FALSE;
        }
        boolean booleanValue = ((Boolean) OooO0oo).booleanValue();
        Log.w("ThanoxLite", "canWrite? " + booleanValue);
        lr lrVar = new lr(logDir.getAbsolutePath());
        lrVar.OooOOOo = new q93("thanoxlite-core.log", 1);
        lrVar.OooOOo = new e86(16);
        lrVar.OooOOoo = new dz0();
        zy2 OooO0oo2 = lrVar.OooO0oo();
        if (!booleanValue) {
            OooO0oo2 = null;
        }
        ArrayList o0OO00O = sy.o0OO00O(new r47[]{obj, OooO0oo2});
        f55 f55Var = new f55();
        f55Var.OooO00o = Target.SIZE_ORIGINAL;
        f55Var.OooO0O0 = "ThanoxLite";
        f55 OooO00o = f55Var.OooO00o();
        r47[] r47VarArr = (r47[]) o0OO00O.toArray(new r47[0]);
        zsa.OooooOO(OooO00o, (r47[]) Arrays.copyOf(r47VarArr, r47VarArr.length));
        LoggerKt.setLogAdapter(new o0OO0O0(5));
        appendDebugLogHeader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final z8a initLog$lambda$3(int i, String str, String str2) {
        v34.OooOoO(str, "tag");
        v34.OooOoO(str2, NotificationCompat.CATEGORY_MESSAGE);
        if (i != 4) {
            if (i != 5) {
                if (i == 6) {
                    zsa.Oooo0O0(str + " " + str2);
                }
            } else {
                zsa.o0ooOOo(str + " " + str2);
            }
        } else {
            zsa.Ooooo0o(str + " " + str2);
        }
        return z8a.OooO00o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final yp9 service_delegate$lambda$0() {
        return new yp9();
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void attachAppBinder(IApp iApp) {
        v34.OooOoO(iApp, "binder");
        getService().attachAppBinder(iApp);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void destroy() {
        getService().destroy();
        throw null;
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public String fingerPrint() {
        return BuildProp.THANOS_BUILD_FINGERPRINT;
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void forceStopPackage(Pkg pkg, String str) {
        v34.OooOoO(pkg, T.Actions.ACTION_LOCKER_VERIFY_EXTRA_PACKAGE);
        getService().forceStopPackage(pkg, str);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void forceStopPackages(List<? extends Pkg> list, String str) {
        v34.OooOoO(list, "pkgs");
        v34.OooOoO(str, "reason");
        getService().forceStopPackages(list, str);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void freezePkgs(List<? extends Pkg> list, boolean z) {
        v34.OooOoO(list, "pkgs");
        getService().freezePkgs(list, z);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<ComponentInfo> getActivities(int i, String str) {
        return getService().getActivities(i, str);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<ComponentInfo> getActivitiesInBatch(int i, String str, int i2, int i3) {
        return getService().getActivitiesInBatch(i, str, i2, i3);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<Pkg> getAllPkgs(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        return getService().getAllPkgs(bArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public Bitmap getAppIcon(String str, int i) {
        v34.OooOoO(str, "pkgName");
        return getService().getAppIcon(str, i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public AppInfo getAppInfoForUid(String str, int i) {
        v34.OooOoO(str, "pkgName");
        return getService().getAppInfoForUid(str, i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public AppInfo getAppInfoForUser(String str, int i) {
        v34.OooOoO(str, "pkgName");
        return getService().getAppInfoForUser(str, i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public int getComponentEnabledSetting(int i, ComponentName componentName) {
        v34.OooOoO(componentName, "componentName");
        return getService().getComponentEnabledSetting(i, componentName);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public ActivityManager.MemoryInfo getMemoryInfo(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        return getService().getMemoryInfo(bArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public int getPid(ProcessName processName) {
        v34.OooOoO(processName, "processName");
        return getService().getPid(processName);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public long[] getProcessPss(int[] iArr) {
        v34.OooOoO(iArr, "pids");
        return getService().getProcessPss(iArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<Pkg> getRunningAppPackages(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        return getService().getRunningAppPackages(bArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public ProcessRecord[] getRunningAppProcess(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        getService().getClass();
        return new ProcessRecord[0];
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<RunningAppProcessInfoCompat> getRunningAppProcessLegacy(int[] iArr, String str, String str2, ICallback iCallback) {
        v34.OooOoO(str2, "deviceId");
        v34.OooOoO(iCallback, "callback");
        return getService().getRunningAppProcessLegacy(iArr, str, str2, iCallback);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public int getRunningAppsCount(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        return getService().getRunningAppsCount(bArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<ActivityManager.RunningServiceInfo> getRunningServiceLegacy(int i) {
        return getService().getRunningServiceLegacy(i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<Pkg> getSFUnSelectedPkgs() {
        return getService().getSFUnSelectedPkgs();
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public SwapInfo getSwapInfo(byte[] bArr) {
        v34.OooOoO(bArr, "data");
        return getService().getSwapInfo(bArr);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public float getTotalCpuPercent(boolean z) {
        return getService().getTotalCpuPercent(z);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public TrafficStats getUidTrafficStats(int i) {
        return getService().getUidTrafficStats(i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public String getVersionName() {
        return BuildProp.THANOS_VERSION_NAME;
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void handleBroadcast(Intent intent) {
        getService().handleBroadcast(intent);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public int killProcessByName(ProcessName processName) {
        v34.OooOoO(processName, "processName");
        return getService().killProcessByName(processName);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void launchFreezedAppForUser(String str, int i) {
        v34.OooOoO(str, "pkgName");
        getService().launchFreezedAppForUser(str, i);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public float queryCpuUsageRatio(long[] jArr, boolean z) {
        v34.OooOoO(jArr, "pids");
        return getService().queryCpuUsageRatio(jArr, z);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public List<ProcessCpuUsageStats> queryProcessCpuUsageStats(long[] jArr, boolean z) {
        v34.OooOoO(jArr, "pids");
        return getService().queryProcessCpuUsageStats(jArr, z);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void registerPkgStateChangeListener(IPkgChangeListener iPkgChangeListener) {
        getService().registerPkgStateChangeListener(iPkgChangeListener);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void setComponentEnabledSetting(int i, ComponentName componentName, int i2, int i3) {
        getService().setComponentEnabledSetting(i, componentName, i2, i3);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public boolean stopService(Intent intent) {
        v34.OooOoO(intent, "intent");
        getService().getClass();
        return false;
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void syncBCSettings(byte[] bArr, String str, String str2, ICallback iCallback) {
        v34.OooOoO(bArr, "data");
        v34.OooOoO(str, "code");
        v34.OooOoO(str2, "deviceId");
        v34.OooOoO(iCallback, "callback");
        getService().syncBCSettings(bArr, str, str2, iCallback);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void syncSFSettings(byte[] bArr, String str, String str2, ICallback iCallback) {
        v34.OooOoO(bArr, "data");
        v34.OooOoO(str, "code");
        v34.OooOoO(str2, "deviceId");
        v34.OooOoO(iCallback, "callback");
        getService().syncSFSettings(bArr, str, str2, iCallback);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void unregisterPkgStateChangeListener(IPkgChangeListener iPkgChangeListener) {
        getService().unregisterPkgStateChangeListener(iPkgChangeListener);
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void updateProcessCpuUsageStats() {
        getService().updateProcessCpuUsageStats();
    }

    @Override // github.tornaco.android.thanos.core.IThanosLite
    public void writeLogsTo(ParcelFileDescriptor parcelFileDescriptor) {
        v34.OooOoO(parcelFileDescriptor, "pfd");
        getService().writeLogsTo(parcelFileDescriptor);
    }
}