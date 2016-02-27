package cobrakai.com.miyagi.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by m.stanford on 2/26/16.
 */
public class Cache {
    private static final String TAG = Cache.class.getSimpleName();

    private static final long DISK_CACHE_SIZE = 1024 * 1024 * 200; // 200MB
    private static final int VALUE_COUNT = 1;
    private static final int DISK_CACHE_INDEX = 0;

    private static Cache Instance;

    DiskLruCache lruCache;

    private Cache(Context context) {
        try {
            final File diskCacheDir = context.getCacheDir();
        PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        int version = pInfo.versionCode;
            lruCache = DiskLruCache.open(diskCacheDir, version, VALUE_COUNT, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
    }

    public static synchronized Cache getCache(Context context){
        if(Instance == null){
            return new Cache(context);
        }
        return Instance;
    }

    public Object get(String key) {
        DiskLruCache.Snapshot snapshot;

        try {
            snapshot = lruCache.get(key);
            ObjectInputStream in = new ObjectInputStream(snapshot.getInputStream(0));
            return (Object) in.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void put(String key, Object object) {
        DiskLruCache.Editor editor = null;
        try {
            editor = lruCache.edit(key);
            if (editor == null) {
                return;
            }

            ObjectOutputStream out = new ObjectOutputStream(editor.newOutputStream(0));
            out.writeObject(object);
            out.close();
            editor.commit();
        } catch (IOException e) {
            Log.e(TAG, "put: " + key + ":" + "Cache error!");
        }
    }

    private String convertUriToKey(Uri uri){
        String temp = uri.toString();
        temp = temp.replaceAll("[^A-Za-z0-9]", "");
        temp = temp.toLowerCase();
        if(temp.length() > 63){
            temp = temp.substring(0,63);
        }
        return temp;
    }
}
