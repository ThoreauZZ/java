// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/thoreau/protobuf/vo/user.proto

package com.thoreau.protobuf.generated.vo;

public final class UserProto {
  private UserProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_User_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_User_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_User_PhoneNumber_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_User_PhoneNumber_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"com/thoreau/protobuf/vo/user.proto\022\005pr" +
      "oto\"\273\001\n\004User\022\021\n\tfirstName\030\001 \001(\t\022\020\n\010lastN" +
      "ame\030\002 \001(\t\022\024\n\014emailAddress\030\003 \001(\t\022\023\n\013homeA" +
      "ddress\030\004 \001(\t\022-\n\014phoneNumbers\030\005 \003(\0132\027.pro" +
      "to.User.PhoneNumber\0324\n\013PhoneNumber\022\020\n\010ar" +
      "eaCode\030\001 \001(\005\022\023\n\013phoneNumber\030\002 \001(\005B0\n!com" +
      ".thoreau.protobuf.generated.voB\tUserProt" +
      "oP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_User_descriptor,
        new java.lang.String[] { "FirstName", "LastName", "EmailAddress", "HomeAddress", "PhoneNumbers", });
    internal_static_proto_User_PhoneNumber_descriptor =
      internal_static_proto_User_descriptor.getNestedTypes().get(0);
    internal_static_proto_User_PhoneNumber_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_User_PhoneNumber_descriptor,
        new java.lang.String[] { "AreaCode", "PhoneNumber", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
