package com.mango.trip.repository.custom

import com.mango.trip.entity.City
import com.mango.trip.entity.QCity.city
import com.mango.trip.entity.QCountry.country
import com.mango.trip.model.SearchCityDto
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory

class CustomCityRepositoryImpl(
    private val factory: JPAQueryFactory,
): CustomCityRepository {
    override fun search(searchCityDto: SearchCityDto): List<City> {
        return factory.select(city)
            .from(city)
            .innerJoin(country).on(country.eq(city.country))
            .where(
                likeCityName(searchCityDto.keyword),
                cursor(searchCityDto.cursor)
            )
            .orderBy(city.id.desc())
            .offset(searchCityDto.pageable.offset)
            .limit(searchCityDto.pageable.pageSize.toLong())
            .fetch()
    }

    private fun likeCityName(keyword: String?): BooleanExpression? {
        return keyword?.let { city.name.like(it) }
    }

    private fun cursor(cursor : String?) : BooleanExpression? {
        return cursor?.let{ city.id.gt(cursor.toLong()) }
    }
}