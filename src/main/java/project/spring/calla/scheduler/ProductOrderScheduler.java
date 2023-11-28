package project.spring.calla.scheduler;

import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.service.ProductOrderService;

@Component
public class ProductOrderScheduler {
	private static final Logger logger 
		= LoggerFactory.getLogger(ProductOrderScheduler.class);
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Scheduled(fixedRate = 60 * 60 * 1000) // 1�ð�
	public void updateDeliveryStatus() {
		
		logger.info("updateDeliveryStatus() ȣ��");
		
		// ProductOrderVO ��������
		List<ProductOrderVO> orders = productOrderService.read();
		logger.info("orders = " + orders);
		// ���� �ð�
		LocalDateTime currentTime = LocalDateTime.now();
		
		for(ProductOrderVO order : orders) {
			Date orderTime = order.getProductOrderCreatedDate(); // ������ �ð�
			Instant instant = orderTime.toInstant(); // Date�� Instant�� 
			LocalDateTime afterOrderTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime(); // Instant�� LocalDateTime���� 
			LocalDateTime oneMinuteAfterOrderTime = afterOrderTime.plusHours(24); // ���Žð� + 24�ð�
			
			if(currentTime.isAfter(oneMinuteAfterOrderTime)) {
				String currentStatus = order.getDeliveryStatus();
				String newDeliveryStatus;
				
				if(currentStatus.equals("����غ���")) {
					newDeliveryStatus = "���Ϸ�";
				} else if(currentStatus.equals("���Ϸ�")) {
					newDeliveryStatus = "�����";
				} else if(currentStatus.equals("�����")) {
					newDeliveryStatus = "��ۿϷ�";
				} else {
					newDeliveryStatus = currentStatus;
				}
				
				logger.info("productOrderId: " + order.getProductOrderId() + ", newDeliveryStatus: " + newDeliveryStatus);
                
				
				productOrderService.updateStatus(order.getProductOrderId(), newDeliveryStatus);
			}
		} // end for()
	} // end updateDeliveryStatus()
	
} // end productOrderScheduler