package com.cc.image.iface;

import com.cc.image.ImageLoadRequest;

public interface NameGenerator {

    /**
     * @param request
     * @return
     */
    public String generateIdentityUrlFor(ImageLoadRequest request);
}
