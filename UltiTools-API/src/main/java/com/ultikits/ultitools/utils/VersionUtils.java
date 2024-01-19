package com.ultikits.ultitools.utils;

import cn.hutool.core.comparator.VersionComparator;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.ultikits.ultitools.entities.PluginEntity;

import static com.ultikits.ultitools.utils.PluginInstallUtils.getPlugin;
import static com.ultikits.ultitools.utils.PluginInstallUtils.getPluginLatestVersion;

public class VersionUtils {

    public static String getUltiToolsNewestVersion() {
        HttpRequest get = HttpUtil.createGet("https://img.shields.io/github/v/release/UltiKits/UltiTools-Reborn");
        HttpResponse httpResponse = get.execute();
        String version = httpResponse.body().split(">v")[1].split("<")[0];
        httpResponse.close();
        return version;
    }

    public static boolean pluginHasUpdate(String pluginIdString, String currentVersion) {
        PluginEntity plugin = getPlugin(pluginIdString);
        if (plugin == null) {
            return false;
        }
        String pluginLatestVersion = getPluginLatestVersion(pluginIdString);
        return new VersionComparator().compare(currentVersion, pluginLatestVersion) < 0;
    }
}
