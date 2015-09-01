package cc.ichoice2.web.buss.demo.service;


public interface DemoService {
	public void save();
	public void delete();
    public void create(String tableName,String bussPackage,String entityPackage,String entityDescription,String[] templates,String[] paths);

}
