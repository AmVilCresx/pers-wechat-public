package pers.avc.wechat.admin.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import pers.avc.wechat.admin.contants.MediaType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:jzaofox@foxmail.com">amvilcresx</a>
 */
@MappedTypes(MediaType.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class MediaTypeHandler implements TypeHandler<MediaType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, MediaType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getTypeId());
    }

    @Override
    public MediaType getResult(ResultSet rs, String columnName) throws SQLException {
        int typeId = rs.getInt(columnName);
        return MediaType.getMediaTypeById(typeId);
    }

    @Override
    public MediaType getResult(ResultSet rs, int columnIndex) throws SQLException {
        int typeId = rs.getInt(columnIndex);
        return MediaType.getMediaTypeById(typeId);
    }

    @Override
    public MediaType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int typeId = cs.getInt(columnIndex);
        return MediaType.getMediaTypeById(typeId);
    }
}
