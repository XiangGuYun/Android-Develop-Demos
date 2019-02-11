package com.kotlinlib.bitmap

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.os.Environment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION
import com.bumptech.glide.request.RequestOptions
import com.kotlinlib.other.DensityUtils
import com.kotlinlib.other.ResUtils
import java.io.*
import java.security.MessageDigest


interface BmpUtils {

    /**
     * 显示网络图片
     * @receiver KotlinActivity
     * @param url String 图片地址
     * @param iv ImageView
     * @param thumbnailValue Float 缩略值
     * @param placeholderImg Int 占位图
     * @param errorLoadImg Int 错误图
     */
    fun Activity.showBmp(url: String, iv: ImageView,
                         thumbnailValue: Float = 1.0f,//缩略图
                         placeholderImg: Int = ResUtils.getMipmapId(this, "ic_launcher"),//占位图
                         errorLoadImg: Int = ResUtils.getMipmapId(this, "ic_launcher")//错误图
    ) {
        Glide.with(this)
                .load(url)
                .thumbnail(thumbnailValue)
                .apply(RequestOptions()
                        .placeholder(placeholderImg)
                        .error(errorLoadImg)
                        .override(this.resources.displayMetrics.widthPixels / 4, DensityUtils.dip2px(this,50f))
                        .centerCrop())
                .into(iv)
    }

    /**
     * Bitmap转二进制
     */
    fun Bitmap.toBytes(isPng: Boolean = true, quality: Int = 100): ByteArray {
        val o = ByteArrayOutputStream()
        if (isPng)
            this.compress(Bitmap.CompressFormat.PNG, quality, o)
        else
            this.compress(Bitmap.CompressFormat.JPEG, quality, o)
        return o.toByteArray()
    }

    /**
     * 二进制转Bitmap
     */
    fun ByteArray.toBmp(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    /**
     * Drawable转Bitmap
     */
    fun Drawable.toBmp(): Bitmap {
        return (this as BitmapDrawable).bitmap
    }

    /**
     * Bitmap转Drawable
     */
    fun Bitmap.toDrawable(): Drawable {
        return BitmapDrawable(this)
    }

    /**
     * 保存Bitmap到本地
     */
    fun Bitmap.save(path: String =
                            Environment.getExternalStorageDirectory().toString(),
                    name: String = "default.jpg",
                    isJpeg: Boolean = true,
                    quality: Int = 100) {
        var file = File("$path/$name")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        val fos = FileOutputStream(file)
        this.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.flush()
        fos.close()
    }

    /**
     * 从资源文件中解析Bitmap
     */
    fun Context.bmpFromRes(id: Int, options: BitmapFactory.Options = BitmapFactory.Options()): Bitmap {
        return BitmapFactory.decodeResource(this.resources, id, options)
    }

    /**
     * 从本地路径中解析Bitmap
     * @receiver Context
     * @param path String
     * @param options BitmapFactory.Options
     * @return Bitmap
     */
    fun Context.bmpFromRes(path: String, options: BitmapFactory.Options = BitmapFactory.Options()): Bitmap {
        return BitmapFactory.decodeFile(path, options)
    }

    /**
     * 显示网络图片
     */
    fun showBitmap(ctx: Context, iv: ImageView, imgUrl: String) {
        Glide.with(ctx).load(imgUrl).apply(RequestOptions()
//                .placeholder(placeImg)
//                .error(errorImg)
//                .override(320, 240).
                .priority(Priority.IMMEDIATE)
                .fitCenter())
                .into(iv)
    }


    companion object {

        /**
         * 获取视频文件截图
         * @param path 视频文件的路径
         * @return Bitmap 返回获取的Bitmap
         */
        fun getVideoThumb(path: String, compress: Boolean = true): Bitmap {
            val media = MediaMetadataRetriever()
            media.setDataSource(path)
            val bitmap = media.frameAtTime
            if (!compress) {
                return bitmap
            }
            return BmpCompUtils.compressImage(bitmap)
        }

        fun getVideoInfo(path: String): Triple<String, String, String> {
            val media = MediaMetadataRetriever()
            media.setDataSource(path)
            val duration = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION)
            val width = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
            val height = media.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
            return Triple(width, height, duration)
        }

    //--------------------------------------------------------------------------------------
    /**
     * Bitmap转二进制字符串
     * @param bmp
     * @return
     */
    fun bmpToString(bmp: Bitmap): String {
        val bytes = bmpToBytes(bmp)
        val builder = StringBuilder("[")
        for (i in bytes.indices) {
            if (i != bytes.size - 1) {
                builder.append(bytes[i].toString() + ",")
            } else {
                builder.append(bytes[i].toString() + "]")
            }
        }
        return builder.toString()
    }

    /**
     * 二进制字符串转Bitmap
     * str 二进制字符串
     * @return
     */
    fun strToBitmap(str: String): Bitmap {
        val strs = str.replace("[", "").replace("]", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val newBytes = ByteArray(strs.size)
        for (i in strs.indices) {
            newBytes[i] = java.lang.Byte.parseByte(strs[i])
        }
        return BitmapFactory.decodeByteArray(newBytes, 0, newBytes.size)
    }


    fun bmpToBytes(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

    /**
     * 压缩文件路径下的图片
     * @param filePath 文件路径
     * @param targetW 想要的宽度值
     * @param targetH 想要的高度值
     * @return
     */
    fun decodeBitmap(filePath: String, targetW: Int, targetH: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true//设置只解码图片的边框（宽高）数据，只为测出采样率
        options.inPreferredConfig = Bitmap.Config.RGB_565//设置图片像素格式的首选配置
        BitmapFactory.decodeFile(filePath, options)//预加载
        //获取图片的原始宽高
        val originalW = options.outWidth
        val originalH = options.outHeight
        //设置采样大小
        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

    fun decodeBitmap(filePath: String): Bitmap {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565//设置图片像素格式的首选配置
        options.inSampleSize = 2//设置采样率大小
        return BitmapFactory.decodeFile(filePath, options)
    }

    fun decodeBitmap(ctx: Context, id: Int, targetW: Int, targetH: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true//设置只解码图片的边框（宽高）数据，只为测出采样率
        options.inPreferredConfig = Bitmap.Config.RGB_565//设置图片像素格式的首选配置
        BitmapFactory.decodeResource(ctx.resources, id, options)//预加载
        //获取图片的原始宽高
        val originalW = options.outWidth
        val originalH = options.outHeight
        //设置采样大小
        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(ctx.resources, id, options)
    }

    /**
     * 压缩Bitmap质量
     * @param bitmap
     * @param quality 1-100
     * @return
     */
    fun compressQuality(bitmap: Bitmap, quality: Int): Bitmap {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val bytes = baos.toByteArray()
        try {
            baos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    fun saveBitmapToSDCard(bitmap: Bitmap, fileName: String, quality: Int) {
        val f = File(Environment.getExternalStorageDirectory().toString(), fileName)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(f)
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)
            try {
                out.flush()
                out.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

    }


    fun compressSize(bitmap: Bitmap, targetW: Int, targetH: Int): Bitmap {
        return Bitmap.createScaledBitmap(bitmap, targetW, targetH, true)
    }

    /**
     * 计算采样率
     */
    private fun getSimpleSize(originalW: Int, originalH: Int, targetW: Int, targetH: Int): Int {
        var sampleSize = 1
        if (originalW > originalH && originalW > targetW) {//以宽度来计算采样值
            sampleSize = originalW / targetW
        } else if (originalW < originalH && originalH > targetH) {
            sampleSize = originalH / targetH
        }
        if (sampleSize <= 0) {
            sampleSize = 1
        }
        return sampleSize
    }

    }

    fun loadVideoScreenshot(context: Context, uri: String, imageView: ImageView, frameTimeMicros: Long) {
        val requestOptions = RequestOptions.frameOf(frameTimeMicros)
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST)
        requestOptions.transform(object : BitmapTransformation() {
            override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
                return toTransform
            }

            override fun updateDiskCacheKey(messageDigest: MessageDigest) {
                try {
                    messageDigest.update((context.packageName + "RotateTransform").toByteArray(charset("utf-8")))
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        })
        Glide.with(context).load(uri).apply(requestOptions).into(imageView)
    }

}