package pers.avc.wechat.admin.contants;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
public enum MediaType {
    IMAGE(1,"image"),
    VOICE(2,"voice"),
    VIDEO(3,"video"),
    THUMB(4,"thumb"),
    NEWS(5, "news"),
    ;
    private int typeId;
    private String name;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MediaType(int typeId, String name){
        this.typeId = typeId;
        this.name = name;
    }

    public static MediaType getMediaTypeById(int typeId){
        for (MediaType mediaTypeEnum : MediaType.values()){
            if(mediaTypeEnum.getTypeId() == typeId){
                return mediaTypeEnum;
            }
        }
        return null;
    }

    public static MediaType getTypeByName(String name){
        for (MediaType mediaTypeEnum : MediaType.values()){
            if(mediaTypeEnum.getName().equals(name)){
                return mediaTypeEnum;
            }
        }
        return null;
    }
}
