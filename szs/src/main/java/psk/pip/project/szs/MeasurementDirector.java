// package psk.pip.project.szs;
//
// enum MeasurementType {
// BLOOD_PREASURE, DAILY, BLOOD;
// }
//
//
//
// abstract class Measurement {
// private String title;
//
// String getTitle() {
// return title;
// }
//
// void setTitle(String t) {
// title = t;
// }
// }
//
// interface MeasurementBuilder {
//
// void buildTitleName();
//
// void buildProperties(Object params);
//
// void buildMeasurementParams(Object params);
//
// Measurement getMeasurement();
//
// }
//
// class MeasurementBuildersFactory {
// MeasurementBuilder createMeasurementBuilder(MeasurementType type) throws
// Exception {
//
// switch (type) {
// case BLOOD_PREASURE:
// return new BloodMeasurementBuilder();
// case DAILY:
// return new BloodPreasureMeasurementBuilder();
// case BLOOD:
// return new DailyMeasurementBuilder();
// default:
// throw new Exception("Not implemented yet");
// }
//
// }
// }
//
// public class MeasurementDirector {
// private MeasurementBuilder builder;
// private MeasurementBuildersFactory factory;
//
// public MeasurementDirector() {
// factory = new MeasurementBuildersFactory();
// }
//
// public void setBuilder(MeasurementBuilder builder) {
// this.builder = builder;
// }
//
// Measurement build(MeasurementDTO dto) throws Exception {
// builder = factory.createMeasurementBuilder(dto.getMeasurementType());
// builder.buildTitleName();
// builder.buildProperties(dto.getProperties());
// builder.buildMeasurementParams(dto.getParams());
//
// return builder.getMeasurement();
//
// }
//
// }
