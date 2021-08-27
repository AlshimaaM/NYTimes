package com.example.nytimes.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticleResponse(

	@SerializedName("copyright")
	val copyright: String?,

	@SerializedName("has_more")
	val hasMore: Boolean?,

	@SerializedName("results")
	val results: List<ResultsItem>,

	@SerializedName("num_results")
	val numResults: Int?,

	@SerializedName("status")
	val status: String?
)

data class Link(

	@SerializedName("suggested_link_text")
	val suggestedLinkText: String? ,

	@SerializedName("type")
	val type: String? ,

	@SerializedName("url")
	val url: String
)

data class Multimedia(

	@SerializedName("src")
	val src: String,

	@SerializedName("width")
	val width: Int? ,

	@SerializedName("type")
	val type: String ,

	@SerializedName("height")
	val height: Int?
)

data class ResultsItem(

	@SerializedName("multimedia")
	val multimedia: Multimedia ,

	@SerializedName("date_updated")
	val dateUpdated: String ,

	@SerializedName("display_title")
	val displayTitle: String,

	@SerializedName("link")
	val link: Link ,

	@SerializedName("publication_date")
	val publicationDate: String ,

	@SerializedName("summary_short")
	val summaryShort: String? ,

	@SerializedName("critics_pick")
	val criticsPick: Int?,

	@SerializedName("byline")
	val byline: String,

	@SerializedName("headline")
	val headline: String ,

	@SerializedName("mpaa_rating")
	val mpaaRating: String? ,

	@SerializedName("opening_date")
	val openingDate: String?
)


@Parcelize
data class Article(
		val displayTitle: String,
		val publishedDate: String,
		val updatedDate: String,
		val byline: String,
		val url: String,
		val summaryShort: String?,
		val headline: String,
		val type: String,
		val src: String?
) : Parcelable

fun ArticleResponse.asDomainModel(): List<Article> {

	return results.map { result: ResultsItem ->
		val multimedia = result.multimedia
		Article(
				displayTitle = result.displayTitle,
				publishedDate = result.publicationDate,
				updatedDate = result.dateUpdated,
				byline = result.byline,
			    url = result.link.url,
				headline = result.headline,
				src = multimedia?.src,
				type = multimedia.type ,
				summaryShort = result.summaryShort
		)
	}
}
