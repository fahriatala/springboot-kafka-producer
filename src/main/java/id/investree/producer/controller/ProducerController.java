package id.investree.producer.controller;


import id.investree.producer.model.ProducerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/producer")
public class ProducerController {


	@Autowired
	private KafkaTemplate<String, ProducerModel> kafkaTemplate;

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody ProducerModel producerModel){
		kafkaTemplate.send("myTopic", producerModel);
	}

	@GetMapping
	public List<ProducerModel> getProducer() {
		List<ProducerModel> producerModels = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			ProducerModel producerModel = new ProducerModel();
			producerModel.setField1("field"+i);
			producerModel.setField2("field"+ (i+1));

			producerModels.add(producerModel);
		}
		return producerModels;
	}

}
