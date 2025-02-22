package com.imiguez.moviesearcher.ddl.dtos

import com.google.gson.annotations.SerializedName
import com.imiguez.moviesearcher.ddl.model.MovieDetailsModel

class MovieDetailsDto (
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: CollectionInfo?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("origin_country") val originCountry: List<String>?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry>?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>?,
    @SerializedName("status") val status: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?
) {
    fun toMovieDetailsModel(): MovieDetailsModel {
        return MovieDetailsModel(
            adult = this.adult,
            backdropPath = this.backdropPath,
            genres = this.genres,
            id = this.id,
            overview = this.overview,
            releaseDate = this.releaseDate,
            title = this.title,
            originalTitle = this.originalTitle,
            voteAverage = this.voteAverage,
        )
    }
}

class CollectionInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String
)

class Genre(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

class ProductionCompany(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)

class ProductionCountry(
    @SerializedName("iso_3166_1") val iso3166_1: String,
    @SerializedName("name") val name: String
)

class SpokenLanguage(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso639_1: String,
    @SerializedName("name") val name: String
)
