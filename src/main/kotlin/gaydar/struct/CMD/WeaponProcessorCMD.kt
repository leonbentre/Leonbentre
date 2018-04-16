package leonbentre.structt.CMDd

import leonbentre.deserializer.channell.ActorChannel.Companion.actorHasWeapons
import leonbentre.structt.Actor
import leonbentre.structt.Bunch
import leonbentre.structt.NetGuidCacheObject
import leonbentre.utill.DynamicArray
import leonbentre.utill.debugln

object WeaponProcessorCMD
{
  fun process(actor : Actor, bunch : Bunch, repObj : NetGuidCacheObject?, waitingHandle : Int, data : HashMap<String, Any?>) : Boolean
  {
    try
    {
      with(bunch) {
        when (waitingHandle)
        {
        //AWeaponProcessor
          16   ->
          {//EquippedWeapons
            val arraySize = readUInt16()
            actorHasWeapons.compute(actor.owner!!) { _, equippedWeapons ->
              val equippedWeapons = equippedWeapons?.resize(arraySize) ?: DynamicArray(arraySize)
              var index = readIntPacked()
              while (index != 0)
              {
                val i = index - 1
                val (netguid, _) = readObject()
                equippedWeapons[i] = netguid
                index = readIntPacked()
              }
              equippedWeapons
            }
          }
          17   ->
          {//CurrentWeaponIndex
            val currentWeaponIndex = propertyInt()
//          println("$actor carry $currentWeaponIndex")
          }
          else -> return ActorCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("WeaponProcessor is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}
