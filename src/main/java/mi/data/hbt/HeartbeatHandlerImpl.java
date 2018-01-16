package mi.data.hbt;

import java.util.Map;

public class HeartbeatHandlerImpl implements HeartbeatHandler {

    public Cmder sendHeartBeat(HeartbeatEntity info) {
        HeartbeatListener linstener = HeartbeatListener.getInstance();

        // 添加节点
        if (!linstener.checkNodeValid(info.getNodeID())) {
            linstener.registerNode(info.getNodeID(), info);
        }

        // 其他操作
        Cmder cmder = new Cmder();
        cmder.setNodeID(info.getNodeID());

        System.out.println("current all the nodes: ");
        Map<String, Object> nodes = linstener.getNodes();
        for (Map.Entry e : nodes.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("#handle a heartbeat");
        return cmder;
    }
}
