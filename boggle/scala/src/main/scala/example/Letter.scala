package example

case class Letter(left: Option[Letter], right: Option[Letter], top: Option[Letter], bottom: Option[Letter], used: Boolean = false)