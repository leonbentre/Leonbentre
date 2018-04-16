package leonbentre.structt.CMDd

import leonbentre.structt.Actor
import leonbentre.struct.Bunch
import leonbentre.structt.NetGuidCacheObject
import leonbentre.utill.debugln

object DeathDropItemPackageCMD
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
          }
          16   -> updateItemBag(actor)
          else -> return ActorCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("DeathDropItemPackageCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}
