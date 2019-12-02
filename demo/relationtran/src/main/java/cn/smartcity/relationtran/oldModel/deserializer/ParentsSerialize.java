package cn.smartcity.relationtran.oldModel.deserializer;

import cn.smartcity.relationtran.oldModel.OBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 父对象序列化
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class ParentsSerialize  extends JsonSerializer<List<OBase>> {

	@Override
	public void serialize(List<OBase> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		List<Long> ids = new ArrayList<>();
		value.forEach(v->ids.add(v.getId()));
		List<Parents> result = new ArrayList<>();
		
		for(Long id : ids) {
			Parents parents = new Parents();
			parents.setId(id);
			result.add(parents);
		}
		
		gen.writeObject(result);
	}
	
	 @SuppressWarnings("unused")
	private static class Parents {
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	}

}
