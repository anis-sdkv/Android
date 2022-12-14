package com.example.homeworks

import com.example.homeworks.models.ImageModel

object Repository {
    private val URLs = listOf(
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-44-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-43-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-42-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-39-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-37-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-36-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-35-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-34-for-iPhoneX-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-33-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-30-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-25-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-24-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-15-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-11-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-10-for-iPhone-X-768x1663.jpg",
        "https://www.iphones.ru/wp-content/uploads/2019/04/iOS-stock-3-for-iPhone-X-768x1663.jpg"
    )

    val images = URLs.map { ImageModel(it) }
}