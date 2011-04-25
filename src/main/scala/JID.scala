package us.troutwine.barkety

import scala.util.matching.Regex

case class JID(username:String, domain:String, resource:Option[String])

object JID {
  val re = new Regex("""(?i)(\w+)@([^/]+)/?+(.+)?+""")

  def apply(u:String, d:String) = new JID(u, d, None)
  def apply(u:String, d:String, r:String) = new JID(u, d, Some(r))
  def apply(jid:String) = {
    jid match {
      case re(u,d,r) =>
        if (r == null)
          new JID(u,d,None)
        else
          new JID(u,d, Some(r))
      case _ =>
        throw new RuntimeException("'%s' is not a valid JID".format(jid))
    }
  }

  implicit def jidToString(jid:JID):String = jid match {
    case JID(s, d, Some(r)) => s + "@" + d + "/" + r
    case JID(s, d, None) => s + "@" + d
  }

}
