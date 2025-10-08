package fashionmanager.kim.develop.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "manager-service", url = "http://localhost:8081")
public interface ManagerServiceClient {
}
