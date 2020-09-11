package domain

import domain.ZeeneaApplicationsManager.Application

class ZeeneaApplicationsManager(instancesRepository: InstancesRepository) {

  def getRunningApplications: List[Application] = instancesRepository.getRunningInstances
    .getOrElse(List.empty)
    .groupBy(_.name)
    .map { case (application, instances) => Application(name = application, versions = instances.map(_.version)) }
    .toList

}

object ZeeneaApplicationsManager {

  final case class Application(name: String, versions: List[String])

}
