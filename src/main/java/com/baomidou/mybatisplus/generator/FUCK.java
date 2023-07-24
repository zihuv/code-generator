package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 代码生成器使用示例
 */
public class FUCK {
    /**
     * 若存在yml文件，生成代码；若不存在yml文件，就生成yml文件
     *
     * @param obj  填this就可
     * @param path 当前文件夹的绝对路径
     */
    public static void run(Object obj, String path) {
        FUCKConfig fuckConfig = getFUCKConfigByPath(obj.getClass(), path);
        if (fuckConfig != null) {
            generateCode(fuckConfig);
        }
    }

    public static void tips() {
        System.out.println("======================================================================");
        System.out.println("tips:");
        System.out.println("0.在pom.xml中添加数据库驱动");
        System.out.println("1.复制下列两行的代码");
        System.out.println("2.修改path为当前类的绝对路径");
        System.out.println("3.启动！！！");
        System.out.println("（默认开启lombok和swagger，导入项目请提前添加依赖）");
        System.out.println("======================================================================");
        System.out.println("String path = \"C:\\\\xxx\\\\src\\\\test\\\\java\\\\CodeTest\";");
        System.out.println("FUCK.run(this, path);");
        System.out.println("======================================================================");
    }

    private static FUCKConfig getFUCKConfigByPath(Class<?> clazz, String targetPath) {
        FUCKConfig fuckConfig = null;

        String templatesPath = "/templates/FUCK.yml";

        // Judging if there is a YAML file in the folder

        targetPath = modifyFilePath(targetPath);
        File yamlFile = new File(targetPath);

        if (!yamlFile.exists()) {
            // If it doesn't exist, copy the FUCK.yml file from the /templates folder to the specified folder
            try {
                copyFileFromClasspath(templatesPath, yamlFile.getAbsolutePath(), clazz);
                System.out.println("======================================================================");
                System.out.println("未检测到当前文件夹有yml文件，系统自动将为您生成！！！");
                System.out.println("FUCK.yml 文件生成成功！！！");
                System.out.println("修改yml配置文件后，再次运行FUCK.run()！！！");
                System.out.println("======================================================================");
            } catch (IOException e) {
                System.err.println("Failed to copy YAML file: " + e.getMessage());
            }
        } else {
            // If it exists, read the YAML file
            try {
                Yaml yml = new Yaml();
                FileReader reader = new FileReader(targetPath);
                BufferedReader buffer = new BufferedReader(reader);
                Map<String, Object> map = yml.load(buffer);

                LinkedHashMap<String, Object> globalConfigMap = castToLinkedHashMap(map.get("globalConfig"));
                LinkedHashMap<String, Object> dataSourceConfigMap = castToLinkedHashMap(map.get("dataSourceConfig"));
                LinkedHashMap<String, Object> packageConfigMap = castToLinkedHashMap(map.get("packageConfig"));

                ObjectMapper objectMapper = new ObjectMapper();
                GlobalConfig globalConfig = objectMapper.convertValue(globalConfigMap, GlobalConfig.class);
                DataSourceConfig dataSourceConfig = objectMapper.convertValue(dataSourceConfigMap, DataSourceConfig.class);
                PackageConfig packageConfig = objectMapper.convertValue(packageConfigMap, PackageConfig.class);

                fuckConfig = new FUCKConfig(globalConfig, dataSourceConfig, packageConfig);

                buffer.close();
                reader.close();
            } catch (IOException e) {
                System.err.println("Failed to read YAML file: " + e.getMessage());
            }
        }
        return fuckConfig;
    }

    /**
     * modify文件路径。若以.java结尾或为文件夹，则将该路径末尾修改为FUCK.yml
     *
     * @param filePath 文件路径
     * @return java.lang.String
     */
    public static String modifyFilePath(String filePath) {
        // 判断是否为文件夹（目录）
        File file = new File(filePath);
        if (file.isDirectory()) {
            // 如果是文件夹，直接在末尾添加 "FUCK.yml"
            return filePath + File.separator + "FUCK.yml";
        } else {
            // 如果不是文件夹，执行原来的替换逻辑
            Pattern pattern = Pattern.compile("\\b\\w+\\.java\\b", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(filePath);
            if (matcher.find()) {
                String oldFileName = matcher.group();
                return filePath.replace(oldFileName, "FUCK.yml");
            }
        }
        // 如果没有找到匹配的文件名且不是文件夹，返回原始路径
        return filePath;
    }

    /**
     * 复制YML文件
     *
     * @param sourcePath      Source path
     * @param destinationPath Destination path
     */
    private static void copyFileFromClasspath(String sourcePath, String destinationPath, Class<?> clazz) throws IOException {
        InputStream inputStream = clazz.getResourceAsStream(sourcePath);
        OutputStream outputStream = Files.newOutputStream(Paths.get(destinationPath));

        if (inputStream == null || outputStream == null) {
            throw new IOException();
        }

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    /**
     * 将对象转换成LinkedHashMap
     *
     * @param obj 需要转换成LinkedHashMap的对象
     * @return java.util.LinkedHashMap<java.lang.String, java.lang.Object>
     */
    @SuppressWarnings("unchecked")
    private static LinkedHashMap<String, Object> castToLinkedHashMap(Object obj) {
        if (obj instanceof LinkedHashMap) {
            return (LinkedHashMap<String, Object>) obj;
        } else {
            throw new ClassCastException("Object cannot be cast to LinkedHashMap<String, Object>.");
        }
    }

    /**
     * 代码生成器（基于yml文件配置）
     *
     * @param fuckConfig 配置文件
     */
    private static void generateCode(FUCKConfig fuckConfig) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 获取各种配置
        GlobalConfig globalConfig = fuckConfig.getGlobalConfig();
        DataSourceConfig dataSourceConfig = fuckConfig.getDataSourceConfig();
        PackageConfig packageConfig = fuckConfig.getPackageConfig();

        globalConfig.setSwagger(true); // 启动swagger

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.entityBuilder().enableLombok().enableSerialVersionUID(); // 启用lombok,序列化
        strategyConfig.controllerBuilder().enableRestStyle(); // rest风格
        strategyConfig.serviceBuilder().formatServiceFileName("%sService"); // 命名为xxxService形式

        // 设置模板
        TemplateConfig.Builder tplBuilder = new TemplateConfig.Builder();
        tplBuilder.disable(TemplateType.XML); // 禁止mapper.xml文件生成
        TemplateConfig templateConfig = tplBuilder.build();

        // 添加实例
        mpg.setDataSource(dataSourceConfig);
        mpg.setPackageInfo(packageConfig);
        mpg.setGlobalConfig(globalConfig);
        mpg.setStrategy(strategyConfig);
        mpg.setTemplate(templateConfig);

        // 执行（使用Freemarker模板引擎）
        mpg.execute(new FreemarkerTemplateEngine());
    }

    /**
     * 代码生成器（以代码的形式启动示例）
     */
    private static void generateCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String outputDir = "C:\\Users\\10413\\Desktop\\demo-final\\src\\main\\java\\com\\baomidou";
        String author = "zihuv";
        GlobalConfig globalConfig = new GlobalConfig(outputDir, author);
        globalConfig.setSwagger(true); // 启动swagger
        globalConfig.setOpen(false); // 关闭自动打开文件夹

        // 数据库配置
        String url = "jdbc:mysql://47.120.9.134:3306/demo?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String username = "root";
        String password = "123456";
        DataSourceConfig dataSourceConfig = new DataSourceConfig(url, username, password);

        // 包配置
        String parent = "com.baidu"; // 父包路径
        String moduleName = "demo"; // 模块名称
        PackageConfig packageConfig = new PackageConfig(parent, moduleName);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.entityBuilder().enableLombok().enableSerialVersionUID(); // 启用lombok,序列化
        strategyConfig.controllerBuilder().enableRestStyle(); // rest风格
        strategyConfig.serviceBuilder().formatServiceFileName("%sService"); // 命名为xxxService形式

        // 设置模板
        TemplateConfig.Builder tplBuilder = new TemplateConfig.Builder();
        tplBuilder.disable(TemplateType.XML); // 禁止mapper.xml文件生成
        TemplateConfig templateConfig = tplBuilder.build();

        // 添加实例
        mpg.setDataSource(dataSourceConfig);
        mpg.setPackageInfo(packageConfig);
        mpg.setGlobalConfig(globalConfig);
        mpg.setStrategy(strategyConfig);
        mpg.setTemplate(templateConfig);

        // 执行（使用Freemarker模板引擎）
        mpg.execute(new FreemarkerTemplateEngine());
    }
}