package com.stylefeng.guns.config.canal;

public class SimpleClient {

//    public static void main(String[] args) throws Exception {
//        CanalConnector connector = CanalConnectors.newSingleConnector(
//                new InetSocketAddress("106.13.37.72", 11111), "example", "", "");
//
//        connector.connect();
//        connector.subscribe(".*\\..*");
//        connector.rollback();
//
//        while (true) {
//            Message message = connector.getWithoutAck(100);
//            long batchId = message.getId();
//            if (batchId == -1 || message.getEntries().isEmpty()) {
//                System.out.println("sleep"+now());
//                Thread.sleep(1000);
//                continue;
//            }
//            printEntries(message.getEntries());
//            connector.ack(batchId);
//        }
//    }
//
//    private static void printEntries(List<Entry> entries) throws Exception {
//        for (Entry entry : entries) {
//            if (entry.getEntryType() != EntryType.ROWDATA) {
//                continue;
//            }
//
//            RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
//            for (RowData rowData : rowChange.getRowDatasList()) {
//                switch (rowChange.getEventType()) {
//                    case INSERT:
//                    case UPDATE:
//                        System.out.print("UPSERT ");
//                        printColumns(rowData.getAfterColumnsList());
//
//                        if ("retl_buffer".equals(entry.getHeader().getTableName())) {
//                            String tableName = rowData.getAfterColumns(1).getValue();
//                            String pkValue = rowData.getAfterColumns(2).getValue();
//                            System.out.println("SELECT * FROM " + tableName + " WHERE id = " + pkValue);
//                        }
//                        break;
//
//                    case DELETE:
//                        System.out.print("DELETE ");
//                        printColumns(rowData.getBeforeColumnsList());
//                        break;
//
//                    default:
//                        break;
//                }
//            }
//        }
//    }
//
//    private static void printColumns(List<Column> columns) {
//        String line = columns.stream()
//                .map(column -> column.getName() + "=" + column.getValue())
//                .collect(Collectors.joining(","));
//        System.out.println(line);
//    }

}
