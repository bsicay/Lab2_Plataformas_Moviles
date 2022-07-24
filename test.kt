// UNIVERSIDAD DEL VALLE DE GUATEMALA
// BRANDON SICAY - 21757
// PROGRAMACION DE PLATAFORMAS MOVILES

import org.junit.Test
import kotlin.test.*

class LabTest {
    @Test
    fun `Given a list, whem it has multiple elements, then result list should be correct`() {
        val result = processList(
            listOf(10, "Enero", null, true)
        )
		assertNotNull(result)
        assertTrue(result.size == 3)
        assertTrue(
             result.get(0).originalPos == 0
             && result.get(0).originalValue == 10
             && result.get(0).type?.lowercase() == "entero"
             && result.get(0).info?.lowercase() == "m10" 
         )
         
         assertTrue(
             result.get(1).originalPos == 1
             && result.get(1).originalValue == "Enero"
             && result.get(1).type?.lowercase() == "cadena"
             && result.get(1).info?.lowercase() == "l5" 
         )
         assertTrue(
             result.get(2).originalPos == 3
             && result.get(2).originalValue == true
             && result.get(2).type?.lowercase() == "booleano"
             && result.get(2).info?.lowercase() == "verdadero" 
         )         
    }
}

// No tocar esta clase ---
data class ItemData(
    var originalPos: Int,
    var originalValue: Any,
	var type: String? = null,
    var info: String? = null
)
// -----------------------

fun processList(inputList: List<Any?>?): List<ItemData>? {
    val myArray = ArrayList<ItemData>()
    inputList?.let {
        // lista viene vacia o lista viene lleno de elementos nulos
        if (it.isEmpty()) return emptyList() else if (it.filterNotNull().isEmpty()) return emptyList()
        for ((index,value) in it.withIndex()){
            when (value) {
                is String -> {
                    myArray.add(ItemData(originalPos = index, originalValue = value, type = "cadena", info = "L"+"$value".length))
                }
                is Int -> {
                    myArray.add(ItemData(originalPos = index, originalValue = value, type = "entero", info = if(value%10 == 0) "M10" else if (value%5 == 0) "M5" else if (value%2 == 0) "M2" else null))
                }
                is Boolean -> myArray.add(ItemData(originalPos = index, originalValue = value, type = "booleano", info = if(value) "Verdadero" else "Falso"))
                else -> {
                    value?.let {
                        myArray.add(ItemData(originalPos = index, originalValue = value, type = null, info = null))
                    } ?: run {
                        myArray.add(ItemData(originalPos = index, originalValue = "", type = null, info = null))
                        //removemos el elemento despues de agregarlo ya que se busca mantener continuidad en indices
                        myArray.removeAt(index)
                    }
                }
              }
        }
        // si la entrada es solo un elemento nulo
    } ?: run {
        return null
    }
        return myArray
}
