package name.lemerdy.sebastian.datamunging

case class WeatherLine(day: String, maxTemperature: Long, minTemperature: Long) {

  val spread: Long = maxTemperature - minTemperature

}
