package com.khuntalocal.app.data

import com.khuntalocal.app.data.model.AlertItem
import com.khuntalocal.app.data.model.AlertType
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.data.model.Language
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.model.Reporter
import com.khuntalocal.app.data.model.VerificationStatus

/**
 * Static seed content used by [com.khuntalocal.app.data.repository.MockNewsRepository]
 * so the UI is fully explorable before the Laravel API exists. Image URLs use
 * picsum.photos seeds so they resolve on a networked device and degrade to
 * colored placeholders offline.
 */
internal object SampleData {

    private fun img(seed: String) = "https://picsum.photos/seed/$seed/800/500"

    private val amit = Reporter(
        id = "u_amit",
        name = "Amit Kumar",
        location = "Khunta, Mayurbhanj",
        isVerifiedReporter = true,
    )
    private val ravi = Reporter(
        id = "u_ravi",
        name = "Ravi Behera",
        location = "Khunta",
        isVerifiedReporter = true,
    )
    private val sunita = Reporter(
        id = "u_sunita",
        name = "Sunita Das",
        location = "Khunta",
        isVerifiedReporter = false,
    )
    private val community = Reporter(
        id = "u_comm",
        name = "Community Member",
        location = "Khunta",
        isVerifiedReporter = false,
    )

    val articles: List<NewsArticle> = listOf(
        NewsArticle(
            id = "n_rain",
            headline = "Heavy rainfall affects several areas of Khunta",
            summary = "Continuous rainfall since early morning has caused waterlogging " +
                "across low-lying parts of Khunta town.",
            body = "Heavy rainfall was reported across Khunta and nearby panchayats since " +
                "early this morning, leading to temporary waterlogging on the main market " +
                "road and near the bus stand. Local residents have been advised to avoid " +
                "unnecessary travel. The block administration said drainage teams have been " +
                "deployed and the situation is being monitored.",
            category = Category.WEATHER,
            status = VerificationStatus.VERIFIED,
            isBreaking = true,
            locationName = "Khunta, Mayurbhanj",
            imageUrl = img("rain"),
            timeAgo = "18 min ago",
            reporter = amit,
            views = 2410,
            likes = 186,
            comments = 23,
            relatedReportsCount = 12,
            language = Language.ODIA,
        ),
        NewsArticle(
            id = "n_landslide",
            headline = "Road blocked due to landslide near Khunta Ghat",
            summary = "A stretch of the ghat road is impassable after loose soil and rocks " +
                "slid onto the carriageway overnight.",
            body = "Traffic on the Khunta Ghat road came to a halt after a landslide " +
                "deposited debris across both lanes. No injuries have been reported. Officials " +
                "say clearing work is underway and light vehicles may be diverted through the " +
                "village route until the road reopens.",
            category = Category.BREAKING,
            status = VerificationStatus.VERIFIED,
            isBreaking = true,
            locationName = "Khunta, Mayurbhanj",
            imageUrl = img("landslide"),
            timeAgo = "32 min ago",
            reporter = ravi,
            views = 2400,
            likes = 186,
            comments = 23,
            relatedReportsCount = 17,
        ),
        NewsArticle(
            id = "n_school",
            headline = "Khunta Higher Secondary School celebrates Annual Function",
            summary = "Students and parents gathered for a colourful cultural evening " +
                "marking the school's annual day.",
            body = "The annual function of Khunta Higher Secondary School featured cultural " +
                "performances, prize distribution for meritorious students, and a speech by " +
                "the chief guest encouraging students to take up local reporting and civic " +
                "participation.",
            category = Category.EDUCATION,
            status = VerificationStatus.VERIFIED,
            isBreaking = false,
            locationName = "Khunta",
            imageUrl = img("school"),
            timeAgo = "1 hr ago",
            reporter = sunita,
            views = 1800,
            likes = 124,
            comments = 15,
        ),
        NewsArticle(
            id = "n_football",
            headline = "Khunta United wins the local football tournament",
            summary = "The home side lifted the trophy after a tense final decided in the " +
                "closing minutes.",
            body = "Khunta United defeated a strong visiting side to win this year's local " +
                "football tournament. A large crowd turned out at the ground to cheer the " +
                "teams. Organisers thanked volunteers and local sponsors for supporting the " +
                "event.",
            category = Category.SPORTS,
            status = VerificationStatus.COMMUNITY_REPORT,
            isBreaking = false,
            locationName = "Khunta",
            imageUrl = img("football"),
            timeAgo = "2 hr ago",
            reporter = community,
            views = 1200,
            likes = 98,
            comments = 12,
        ),
        NewsArticle(
            id = "n_lightrain",
            headline = "Light rain expected in Khunta throughout the day",
            summary = "The local forecast points to intermittent light showers with cooler " +
                "temperatures.",
            body = "Weather updates suggest light rain is likely to continue on and off " +
                "through the day across Khunta and surrounding areas. Commuters may want to " +
                "carry rain protection.",
            category = Category.WEATHER,
            status = VerificationStatus.COMMUNITY_REPORT,
            isBreaking = false,
            locationName = "Khunta",
            imageUrl = img("weather"),
            timeAgo = "3 hr ago",
            reporter = community,
            views = 640,
            likes = 41,
            comments = 4,
        ),
        NewsArticle(
            id = "n_market",
            headline = "Weekly market to shift timing during festival week",
            summary = "Traders have announced revised hours for the weekly haat ahead of the " +
                "festival.",
            body = "The weekly market in Khunta will operate on adjusted timings during the " +
                "upcoming festival week to manage larger crowds. Shoppers are advised to plan " +
                "visits accordingly.",
            category = Category.LOCAL,
            status = VerificationStatus.UNDER_REVIEW,
            isBreaking = false,
            locationName = "Khunta",
            imageUrl = img("market"),
            timeAgo = "5 hr ago",
            reporter = sunita,
            views = 305,
            likes = 22,
            comments = 3,
        ),
    )

    val alerts: List<AlertItem> = listOf(
        AlertItem(
            id = "a1",
            type = AlertType.BREAKING,
            title = "Breaking News",
            body = "Road blocked due to landslide near Khunta Ghat.",
            timeAgo = "32 min ago",
            unread = true,
        ),
        AlertItem(
            id = "a2",
            type = AlertType.NEWS_VERIFIED,
            title = "Your report was verified",
            body = "\"Heavy rainfall affects several areas of Khunta\" is now published as Verified.",
            timeAgo = "1 hr ago",
            unread = true,
        ),
        AlertItem(
            id = "a3",
            type = AlertType.NEARBY,
            title = "Nearby story",
            body = "Khunta United wins the local football tournament.",
            timeAgo = "2 hr ago",
            unread = true,
        ),
        AlertItem(
            id = "a4",
            type = AlertType.COMMENT_REPLY,
            title = "New reply to your comment",
            body = "Ravi replied: \"Thanks for the update, stay safe.\"",
            timeAgo = "4 hr ago",
            unread = false,
        ),
        AlertItem(
            id = "a5",
            type = AlertType.REPORTER_APPROVED,
            title = "Reporter application approved",
            body = "You are now a Verified Reporter for Khunta.",
            timeAgo = "Yesterday",
            unread = false,
        ),
    )
}
