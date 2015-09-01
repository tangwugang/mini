package cc.ichoice.minidao.util;

/**
 * Created by twg on 15/8/24.
 */
public enum TemplateStateEnum {
    ENTITY(1,"entityTemplate"),CONTROLLER(2,"controller"),SERVICE(3,"serviceTemplate"),SERVICEIMPL(4,"serviceImplTemplate"),DAO(5,"daoTemplate"),MAPPER(6,"mapperTemplate");
    private int key;
    private String value;

    TemplateStateEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
