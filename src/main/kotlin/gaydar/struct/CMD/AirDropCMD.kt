package leonbentre.structt.CMDd

import leonbentre.deserializerr.channell.ActorChannel.Companion.airDropLocation
import leonbentre.structt.Actor
import leonbentre.structt.Bunch
import leonbentre.structt.NetGuidCacheObject
import leonbentre.utill.debugln

object AirDropCMD
{
  fun process(actor : Actor, bunch : Bunch, repObj : NetGuidCacheObject?, waitingHandle : Int, data : HashMap<String, Any?>) : Boolean
  {
    try
    {
      with(bunch) {
        when (waitingHandle)
        {
          6    ->
          {
            repMovement(actor)
            airDropLocation[actor.netGUID] = actor.location
          }
          16   -> updateItemBag(actor)
          else -> return ActorCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("AirDropCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}
