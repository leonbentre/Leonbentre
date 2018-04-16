package leonbentre.structt.CMDd

import leonbentre.structt.Actor
import leonbentre.structt.Bunch
import leonbentre.structt.NetGuidCacheObject
import leonbentre.structt.Vehicle
import leonbentre.utill.debugln

object VehicleCMD
{
  fun process(actor : Actor, bunch : Bunch, repObj : NetGuidCacheObject?, waitingHandle : Int, data : HashMap<String, Any?>) : Boolean
  {
    try
    {
      actor as Vehicle
      with(bunch) {
        when (waitingHandle)
        {
          16   ->
          {
            val (netguid) = propertyObject()
            actor.driverPlayerState = netguid
          }
          else -> return APawnCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("VehicleCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}
