package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class FUCKConfig {
    private GlobalConfig globalConfig;
    private DataSourceConfig dataSourceConfig;
    private PackageConfig packageConfig;

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public PackageConfig getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfig packageConfig) {
        this.packageConfig = packageConfig;
    }

    public FUCKConfig() {
    }

    public FUCKConfig(GlobalConfig globalConfig, DataSourceConfig dataSourceConfig, PackageConfig packageConfig) {
        this.globalConfig = globalConfig;
        this.dataSourceConfig = dataSourceConfig;
        this.packageConfig = packageConfig;
    }
}