package com.alex.numbercar.model;

import com.google.gson.annotations.SerializedName;

public class ItemsMain {

   @SerializedName("photocar")
   private Photocar[] photocar;

   public ItemsMain(Photocar[] photocar) {
      this.photocar = photocar;
   }

   public Photocar[] getPhotocar() {
      return photocar;
   }

   public void setPhotocar(Photocar[] photocar) {
      this.photocar = photocar;
   }
}