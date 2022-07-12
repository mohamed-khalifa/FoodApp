package com.khalifa.foodapp.data.repository


import com.khalifa.foodapp.data.constant.NetworkStatus
import com.khalifa.foodapp.data.remote.datasource.FoodDetailsRemoteDataSourceImpl
import com.khalifa.foodapp.data.remote.entity.FoodDetailsResponse
import com.khalifa.foodapp.data.remote.entity.Meta
import com.khalifa.foodapp.data.remote.entity.Response
import com.khalifa.foodapp.domain.mapper.FoodDomainMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FoodRepositoryImplTest {

    private val foodDetailsRemoteDataSourceImpl: FoodDetailsRemoteDataSourceImpl =
        mockk(relaxed = true)
    private val mapper: FoodDomainMapper = mockk(relaxed = true)

    private val foodRepositoryImpl =
        FoodRepositoryImpl(foodDetailsRemoteDataSourceImpl, mapper)

    private val foodId = "1"

    @Test
    fun `test apply from FoodDomainMapper is called when calling getFoodDetails from repo`() {
        runBlocking {
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
            coEvery { foodDetailsRemoteDataSourceImpl.getFoodDetails(any()) } returns foodDetailsResponse
            foodRepositoryImpl.getFoodDetails(foodId)
            coVerify { mapper.apply(foodDetailsResponse) }
        }
    }

    @Test
    fun `test datasource getFoodDetails is called from repo`() {

        runBlocking {
            foodRepositoryImpl.getFoodDetails(foodId)
            coVerify { foodDetailsRemoteDataSourceImpl.getFoodDetails(foodId) }
        }
    }

}