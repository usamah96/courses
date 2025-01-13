package com.service.user.feign;

import com.service.user.dto.AlbumResponseModel;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "albums-ws", fallbackFactory = AlbumsFallback.class)
public interface AlbumFeignClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable("id") Long id);
}

//@Component
//class AlbumsFallback implements AlbumFeignClient{
//
//    @Override
//    public List<AlbumResponseModel> getAlbums(Long id) {
//        return Collections.emptyList();
//    }
//}

@Component
class AlbumsFallback implements FallbackFactory<AlbumFeignClient> {

    @Override
    public AlbumFeignClient create(Throwable cause) {
        return new AlbumFeignClientImpl(cause);
    }
}

class AlbumFeignClientImpl implements AlbumFeignClient {

    private final Throwable throwable;

    public AlbumFeignClientImpl(Throwable throwable){
        this.throwable = throwable;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(Long id) {
        System.out.println("AlbumFeignClientException: " + this.throwable.getLocalizedMessage());
        return Collections.emptyList();
    }
}
