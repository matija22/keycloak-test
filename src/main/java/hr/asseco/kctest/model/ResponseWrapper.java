package hr.asseco.kctest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"data", "count"})
public class ResponseWrapper<T> {
	
	@JsonProperty("data")
	private T data;
	@JsonProperty("count")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long count;
	
	public ResponseWrapper() {
	
	}
	
	public ResponseWrapper(T data) {
		this.data = data;
	}
	
	public ResponseWrapper(T data, long count) {
		this.data = data;
		this.count = count;
	}
	
	public T getData() {
		return data;
	}
	
	public Long getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return "ResponseWrapper{" +
				"data=" + data +
				", count=" + count +
				'}';
	}
}