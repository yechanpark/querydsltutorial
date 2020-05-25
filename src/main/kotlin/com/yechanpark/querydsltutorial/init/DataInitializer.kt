package com.yechanpark.querydsltutorial.init

import com.yechanpark.querydsltutorial.repository.CustomEntityRepository
import com.yechanpark.querydsltutorial.repository.OtherCustomEntityRepository
import com.yechanpark.querydsltutorial.entity.CustomEntity
import com.yechanpark.querydsltutorial.entity.OtherCustomEntity
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DataInitializer (
    private val entityRepository: CustomEntityRepository,
    private val otherCustomEntityRepository: OtherCustomEntityRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        for (num in 1..355) {
            entityRepository.save(CustomEntity(dateTime = LocalDateTime.now()))
            otherCustomEntityRepository.save(OtherCustomEntity(dateTimeInOther = LocalDateTime.now().plusSeconds(1)))
        }
    }

}