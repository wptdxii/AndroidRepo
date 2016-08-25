package com.wptdxii.androidrepo.imageloader;

import android.content.Context;

import com.wptdxii.androidrepo.imageloader.glide.GlideImageLoaderStrategy;

/**
 * Created by wptdxii on 2016/8/25 0025.
 */
public class ImageLoaderProvider implements IImageLoaderStrategy {
    private IImageLoaderStrategy mStrategy;

    private ImageLoaderProvider() {
        //当需要更改图片加载框架时直接在这里修改
        mStrategy = new GlideImageLoaderStrategy();
    }


    private static class ImageLoaderProviderHolder {
        private static ImageLoaderProvider instance = new ImageLoaderProvider();
    }

    public static ImageLoaderProvider getInstance() {
        return ImageLoaderProviderHolder.instance;
    }

    @Override
    public void loadImage(Context context, ImageLoaderConfig imageLoaderConfig) {
        mStrategy.loadImage(context, imageLoaderConfig);
    }

    @Override
    public void loadCircleImage(Context context, ImageLoaderConfig imageLoaderConfig) {
        mStrategy.loadCircleImage(context, imageLoaderConfig);
    }

    /**
     * change default ImageLoaderStrategy Implement
     * @param strategy
     */
    public void setLoadImageStrategy(IImageLoaderStrategy strategy) {
        this.mStrategy = strategy;
    }
}
