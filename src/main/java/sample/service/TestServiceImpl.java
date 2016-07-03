package sample.service;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class TestServiceImpl implements TestService, Serializable {

	@Override
	public String test(String data) {
		return data;
	}
}
