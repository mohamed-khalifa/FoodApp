package com.khalifa.foodapp.domain.mapper

import com.khalifa.foodapp.data.constant.NetworkConstants.CONNECT_EXCEPTION
import com.khalifa.foodapp.data.constant.NetworkStatus
import com.khalifa.foodapp.data.remote.entity.FoodDetailsResponse
import com.khalifa.foodapp.data.remote.entity.Meta
import com.khalifa.foodapp.data.remote.entity.Response
import com.khalifa.foodapp.domain.entity.FoodDomainEntities
import org.junit.Assert.assertEquals
import org.junit.Test

class FoodDomainMapperTest {
    @Test
    fun `test FoodResponse Connection failed response should return Failure`() {
        val foodDetailsResponse: NetworkStatus<FoodDetailsResponse> =
            NetworkStatus.Error(CONNECT_EXCEPTION)
        val expected = FoodDomainEntities.Failure(CONNECT_EXCEPTION)
        val actual = FoodDomainMapper().apply(foodDetailsResponse)
        assertEquals(expected, actual)
    }

    @Test
    fun `test FoodResponse success should return Success`() {
        val foodDetailsResponse: NetworkStatus<FoodDetailsResponse> =
            NetworkStatus.Success(
                FoodDetailsResponse(
                    Meta(200),
                    Response(
                        4.012,
                        0.0,
                        2.5,
                        3.04,
                        174.0,
                        "Ricotta cheese",
                        20.0,
                        8.29,
                        0.084,
                        11.26,
                        12.98,
                        0.051,
                        "Whole cheese",
                        0.27
                    )
                )
            )

        val expected: FoodDomainEntities.FoodDomainEntity =

            FoodDomainEntities.FoodDomainEntity(
                "Ricotta cheese",
                "174",
                "3.04g",
                "11.26g",
                "12.98g",
                "0g",
                "0.27g",
                "8.29g",
                "4.01g"
            )

        val actual = FoodDomainMapper().apply(foodDetailsResponse)
        assertEquals(expected, actual)
    }
}