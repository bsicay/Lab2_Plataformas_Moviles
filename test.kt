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
   var inputInfo: String? = null; var inputType: String? = null
   return inputList?.mapIndexed { index, value ->  value?.let {
	    when (value) {
		is String -> {
		    inputType = "cadena"
		    inputInfo = "L${value.length}"
		}
		is Int -> {
		    inputType = "entero"
		    inputInfo = listOf(10,5,2).firstOrNull { value % it == 0 }?.let { "M$it" }
		}
		is Boolean -> {
		    inputType = "booleano"
		    inputInfo = if(value) "Verdadero" else "Falso"
		}
		else -> { inputType = null; inputInfo = null }
	    }
	    ItemData(index, value, inputType, inputInfo)}}?.filterNotNull() //remove the null elements, keeping the index continuity
}
