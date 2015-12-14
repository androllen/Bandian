package com.cc.cache.image.iface;

import com.cc.cache.image.ImageLoadRequest;

public interface NameGenerator {

    /**
     * @param request
     * @return
     */
    public String generateIdentityUrlFor(ImageLoadRequest request);
}
