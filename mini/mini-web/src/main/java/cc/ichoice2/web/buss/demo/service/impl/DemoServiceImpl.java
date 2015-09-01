package cc.ichoice2.web.buss.demo.service.impl;

import cc.ichoice.minidao.codegenerate.factory.CodeGenerateFactory;
import cc.ichoice.minidao.db.MiniDao;
import cc.ichoice2.web.buss.demo.service.DemoService;
import cc.ichoice2.web.buss.model.Demo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("demoService")
//@Transactional
public class DemoServiceImpl implements DemoService {
    private static Logger logger = Logger.getLogger(DemoServiceImpl.class);


	@Autowired
	private MiniDao miniDao;

    @Autowired
    private CodeGenerateFactory codeGenerateFactory;

	public void save() {

		Demo d = new Demo();
		d.setId("402897101aXXX");
		d.setCreateBy("402897fa46c6a1370146c6a1370f0000XX");
		d.setPassword("2222222");
		d.setUserName("测试数据1");
		d = miniDao.queryEntity(d);
		d.setOfficePhone("12345678XX");
		
		Demo d2 = new Demo();
		d2.setId("4028971947f61d550147f6269d07001a");
		d2.setCreateBy("402897fa46c6a1370146c6a1370f0000XX");
		d2.setPassword("2222222");
		d2.setUserName("测试数据1");
//		d = miniDao.queryEntity(d);
		d2.setOfficePhone("12345678XX");
//		System.out.println(miniDao.queryEntityList(d,"updateDate desc", new Object[]{"createBy"}).get(0).getUpdateDate());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("createName", "zyf_01");
		List<Demo> l = new ArrayList<Demo>();
		l.add(d);
		l.add(d2);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", d.getId());
//		System.out.println(miniDao.insertReturnPK(d));
//		System.out.println(miniDao.delete(d, param));
//		System.out.println(miniDao.batchUpdate(l));
//		System.out.println(miniDao.batchSave(l));
//		System.out.println(miniDao.queryCount(Demo.class, params));
	}

    public void create(String tableName,String bussPackage,String entityPackage,String entityDescription,String[] templates,String[] paths) {
        String dbType = miniDao.queryDBType();
        try {
            codeGenerateFactory.generate(tableName,bussPackage,entityPackage,entityDescription,(JdbcTemplate)miniDao.queryJdbcTemplate(),dbType,templates,paths);
        } catch (IOException e) {
        }
    }

    public void delete() {
		
	}

}
