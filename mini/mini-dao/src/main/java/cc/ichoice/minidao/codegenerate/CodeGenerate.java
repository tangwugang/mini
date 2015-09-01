package cc.ichoice.minidao.codegenerate;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

/**
 * Created by twg on 15/8/20.
 */
public interface CodeGenerate {
    /**
     * 根据参数获取文件资料路径
     *
     * @param param 入参
     * @return 返回文件路径
     */
    public String getFileResourcePath(String param);

    /**
     * 根据参数自动生成代码
     *
     * @param tableName         数据库表名
     * @param bussPackage       业务包名
     * @param entityPackage     业务实体包名
     * @param entityDescription 业务实体描述
     * @param jdbcTemplate      spring jdbcTemplate
     * @param dbType            数据库类型
     * @param templates         需要生成的模板集合
     * @param path         需要生成的文件存放路径
     */
    public void generate(String tableName, String bussPackage, String entityPackage, String entityDescription, JdbcTemplate jdbcTemplate, String dbType, String[] templates,String[]path) throws IOException;


}
