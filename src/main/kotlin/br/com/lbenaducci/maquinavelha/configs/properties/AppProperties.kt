package br.com.lbenaducci.maquinavelha.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("config")
class AppProperties(
    val tries: Int,
    val millisWait: Long
)