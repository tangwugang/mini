package cc.ichoice.minidao.codegenerate.factory;

import cc.ichoice.minidao.codegenerate.CodeGenerate;
import cc.ichoice.minidao.db.config.TableHelper;
import cc.ichoice.util.FreemarkerUtil;
import cc.ichoice.util.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by twg on 15/8/20.
 */
@Component
public class CodeGenerateFactory implements CodeGenerate {

    private static Logger logger = Logger.getLogger(CodeGenerateFactory.class);

    public String getFileResourcePath(String param) {
        return ResourceBundle.getBundle("mini_code").getString(param);
    }

    public void generate(String tableName, String bussPackage, String entityPackage, String entityDescription, JdbcTemplate jdbcTemplate, String dbType, String[] templates,String[] paths) throws IOException {
        String entityName = StringUtil.underlineToCamelhump(StringUtil.formatStringRemoveFirst(tableName,"_"));
        String projectPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        StringBuilder filePath = new StringBuilder(projectPath);
        //模板路径
        String templatePath = getFileResourcePath("template_path").replace(".", File.separator);
        filePath.append(templatePath);
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(filePath.toString()));
        // 定义模板变量
        Map<String, Object> model = new HashMap();
        model.put("entityName", entityName);
        model.put("entityDescription", entityDescription);
        model.put("createTime", DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
        model.put("bussPackage", StringUtils.lowerCase(bussPackage));
        model.put("entityPackage", StringUtils.lowerCase(entityPackage));

        model.put("fields", TableHelper.getFields(tableName, jdbcTemplate, dbType));
        model.put("columnData", TableHelper.getColumnDatas(tableName, jdbcTemplate, dbType));
        Map<String,Object> sqlMap = TableHelper.createSql(tableName, jdbcTemplate, dbType);
        model.put("selectByIdsSql",sqlMap.get("selectByIdsSql"));
        model.put("deleteByIdsSql",sqlMap.get("deleteByIdsSql"));
        model.put("deleteByIdSql",sqlMap.get("deleteByIdSql"));
        model.put("updateSql", sqlMap.get("updateSql"));
        model.put("selectSql", sqlMap.get("selectSql"));
        model.put("insertSql", sqlMap.get("insertSql"));
        model.put("whereClause", sqlMap.get("whereClause"));
        model.put("baseUpdateSet", sqlMap.get("baseUpdateSet"));
        model.put("baseColumnList", sqlMap.get("baseColumnList"));
        model.put("tableName",tableName);

        if (null != templates && templates.length > 0) {
            for (String tem : templates) {
                // 生成 Entity
                Template template = cfg.getTemplate("entityTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);

                    filePath = new StringBuilder(paths[0]).delete(paths[0].lastIndexOf("target"),paths[0].length());
                    filePath.append(getFileResourcePath("model_path").replace(".",File.separator)).append(File.separator).append(model.get("bussPackage").toString().replace(".", File.separator)).append(File.separator).append("entity").append(File.separator).append(model.get("entityPackage")).append(File.separator).append(model.get("entityName")).append(".java");
                    if (tem.equals("entityTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据entity模板生成代码异常,原因为："+e);
                }
                //生成 Controller
                template = cfg.getTemplate("controllerTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);
                    filePath = new StringBuilder(paths[1]).delete(paths[1].lastIndexOf("target"),paths[1].length());
                    filePath.append(getFileResourcePath("model_path").replace(".",File.separator)).append(File.separator).append(model.get("bussPackage").toString().replace(".", File.separator)).append(File.separator).append("web").append(File.separator).append(model.get("entityPackage")).append(File.separator).append(model.get("entityName")).append("Controller").append(".java");
                    if (tem.equals("controllerTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据controller模板生成代码异常,原因为："+e);
                }

                //生成 Service
                template = cfg.getTemplate("serviceTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);
                    filePath = new StringBuilder(paths[2]).delete(paths[2].lastIndexOf("target"),paths[2].length());
                    filePath.append(getFileResourcePath("model_path").replace(".",File.separator)).append(File.separator).append(model.get("bussPackage").toString().replace(".", File.separator)).append(File.separator).append("biz").append(File.separator).append(model.get("entityPackage")).append(File.separator).append(model.get("entityName")).append("Service").append(".java");
                    if (tem.equals("serviceTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据service模板生成代码异常,原因为："+e);
                }
                //生成 ServiceImpl
                template = cfg.getTemplate("serviceImplTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);
                    filePath = new StringBuilder(paths[3]).delete(paths[3].lastIndexOf("target"),paths[3].length());
                    filePath.append(getFileResourcePath("model_path").replace(".",File.separator)).append(File.separator).append(model.get("bussPackage").toString().replace(".", File.separator)).append(File.separator).append("biz").append(File.separator).append(model.get("entityPackage")).append(File.separator).append("impl").append(File.separator).append(model.get("entityName")).append("ServiceImpl").append(".java");
                    if (tem.equals("serviceImplTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据serviceImpl模板生成代码异常,原因为："+e);
                }
                //生成 Dao
                template = cfg.getTemplate("daoTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);
                    filePath = new StringBuilder(paths[4]).delete(paths[4].lastIndexOf("target"),paths[4].length());
                    filePath.append(getFileResourcePath("model_path").replace(".",File.separator)).append(File.separator).append(model.get("bussPackage").toString().replace(".", File.separator)).append(File.separator).append("dao").append(File.separator).append(model.get("entityPackage")).append(File.separator).append(model.get("entityName")).append("Dao").append(".java");
                    if (tem.equals("daoTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据dao模板生成代码异常,原因为："+e);
                }

                //生成 Mapper
                template = cfg.getTemplate("mapperTemplate.ftl");
                try {
                    String content = FreemarkerUtil.renderTemplate(template, model);
                    filePath = new StringBuilder(paths[5]).delete(paths[5].lastIndexOf("target"), paths[5].length());
                    filePath.append(getFileResourcePath("mapper_path").replace(".", File.separator)).append(File.separator).append(model.get("entityPackage")).append(File.separator).append(model.get("entityName")).append(".xml");
                    if (tem.equals("mapperTemplate")) {
                        writeFile(content, filePath.toString());
                    }

                } catch (TemplateException e) {
                    logger.error("根据mapper模板生成代码异常,原因为："+e);
                }



            }

        }
    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    private static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (descFileName.endsWith(File.separator)) {
            logger.error(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                logger.error("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件

        try {
            file.delete();
            logger.info("--------成功删除文件---------" + descFileName);
            if (file.createNewFile()) {
                logger.info(descFileName + " 文件创建成功!");
                return true;
            } else {
                logger.info(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (IOException e) {
            logger.error(descFileName + " 文件创建失败!" + e);
            return false;
        }

    }


    /**
     * 将内容写入文件
     *
     * @param content
     * @param filePath
     */
    private static void writeFile(String content, String filePath) {
        try {
            if (createFile(filePath)) {
                FileWriter fileWriter = new FileWriter(filePath, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);
                bufferedWriter.close();
                fileWriter.close();
                logger.info("自动生成文件成功存放路径："+filePath);
            } else {
                logger.info("生成失败，文件已存在！");
            }
        } catch (IOException e) {
            logger.error("生成文件异常,异常原因："+e);
        }
    }
}
