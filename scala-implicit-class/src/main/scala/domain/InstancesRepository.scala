package domain

import domain.InstancesRepository.{Instance, InstancesError}

trait InstancesRepository {

  def getRunningInstances: Either[InstancesError, List[Instance]]

  def getTerminatedInstances: Either[InstancesError, List[Instance]]

}

object InstancesRepository {

  final case class Instance(name: String, version: String)

  sealed trait InstancesError

  case class TechnicalError(cause: String) extends InstancesError

}
