package name.lemerdy.sebastian

import org.scalatest.{FlatSpec, Matchers}

class DependenciesSpec extends FlatSpec with Matchers {

  "Dependencies" should "compute dependencies" in {
    val dependencies = new Dependencies().addDirect('A', 'B', 'C')
                      .addDirect('B', 'C', 'E')
                      .addDirect('C', 'G')
                      .addDirect('D', 'A', 'F')
                      .addDirect('E', 'F')
                      .addDirect('F', 'H')

    dependencies.dependenciesFor('A') should contain inOrderOnly ('B', 'C', 'E', 'F', 'G', 'H')
    dependencies.dependenciesFor('B') should contain inOrderOnly ('C', 'E', 'F', 'G', 'H')
    dependencies.dependenciesFor('C') should contain only 'G'
    dependencies.dependenciesFor('D') should contain inOrderOnly ('A', 'B', 'C', 'E', 'F', 'G', 'H')
    dependencies.dependenciesFor('E') should contain inOrderOnly ('F', 'H')
    dependencies.dependenciesFor('F') should contain only 'H'
  }

}
