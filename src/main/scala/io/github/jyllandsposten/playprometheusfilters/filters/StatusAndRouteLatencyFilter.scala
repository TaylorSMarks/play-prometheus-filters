package io.github.jyllandsposten.playprometheusfilters.filters

import akka.stream.Materializer
import io.github.jyllandsposten.playprometheusfilters.metrics.DefaultPlayUnmatchedDefaults
import io.github.jyllandsposten.playprometheusfilters.metrics.LatencyRequestMetrics.LatencyRequestMetricsBuilder
import io.prometheus.client.CollectorRegistry
import javax.inject.{Inject, Singleton}
import play.api.Configuration

import scala.concurrent.ExecutionContext

/**
  * A [[MetricsFilter]] using a histogram metric to record latency.
  * Latency metric adds 'RouteActionMethod', 'Status', 'Controller', 'Path' and 'Verb' labels.
  */
@Singleton
class StatusAndRouteLatencyFilter @Inject()(registry: CollectorRegistry, configuration: Configuration)(implicit mat: Materializer, ec: ExecutionContext) extends MetricsFilter(configuration) {

  override val metrics = List(
    LatencyRequestMetricsBuilder.build(registry, DefaultPlayUnmatchedDefaults)
  )
}