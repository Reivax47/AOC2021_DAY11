fun main() {
    data class ligne(var mesVal: MutableList<Int>)

    fun part1(input: List<String>): Int {
        val max_x = input[0].length - 1
        val max_y = input.size - 1
        val decal_x = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val decal_y = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
        var octopuses = mutableListOf<ligne>()
        var nb_flashes = 0
        input.forEach {
            var les_int = mutableListOf<Int>()
            it.forEach { it2 ->
                les_int.add(it2.toString().toInt())
            }
            octopuses.add(ligne(mesVal = les_int))
        }

        for (steps in 1..100) {

            octopuses.forEach {
                it.mesVal = it.mesVal.map { it + 1 }.toMutableList()
            }
            while (octopuses.filter { it.mesVal.filter { it > 9 }.isNotEmpty() }.isNotEmpty()) {

                for (y in 0..max_y) {
                    for (x in 0..max_x) {
                        if (octopuses[y].mesVal[x] > 9) {
                            octopuses[y].mesVal[x] = 0
                            nb_flashes++
                            for (cpt in 0..7) {

                                if (x + decal_x[cpt] in 0..max_x && y + decal_y[cpt] in 0..max_y && octopuses[y + decal_y[cpt]].mesVal[x + decal_x[cpt]] != 0) {
                                    octopuses[y + decal_y[cpt]].mesVal[x + decal_x[cpt]]++
                                }
                            }
                        }
                    }
                }
            }
        }
        return nb_flashes
    }

    fun part2(input: List<String>): Int {
        val max_x = input[0].length - 1
        val max_y = input.size - 1
        val nb_octopus = input[0].length * input.size
        val decal_x = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val decal_y = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
        var octopuses = mutableListOf<ligne>()
        var nb_flashes = 0
        var steps = 0
        input.forEach {
            var les_int = mutableListOf<Int>()
            it.forEach { it2 ->
                les_int.add(it2.toString().toInt())
            }
            octopuses.add(ligne(mesVal = les_int))
        }

        while (nb_flashes != nb_octopus) {
            nb_flashes = 0
            steps++
            octopuses.forEach {
                it.mesVal = it.mesVal.map { it + 1 }.toMutableList()
            }
            while (octopuses.filter { it.mesVal.filter { it > 9 }.isNotEmpty() }.isNotEmpty()) {

                for (y in 0..max_y) {
                    for (x in 0..max_x) {
                        if (octopuses[y].mesVal[x] > 9) {
                            octopuses[y].mesVal[x] = 0
                            nb_flashes++
                            for (cpt in 0..7) {

                                if (x + decal_x[cpt] in 0..max_x && y + decal_y[cpt] in 0..max_y && octopuses[y + decal_y[cpt]].mesVal[x + decal_x[cpt]] != 0) {
                                    octopuses[y + decal_y[cpt]].mesVal[x + decal_x[cpt]]++
                                }
                            }
                        }
                    }
                }
            }
        }
        return steps
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))

}
