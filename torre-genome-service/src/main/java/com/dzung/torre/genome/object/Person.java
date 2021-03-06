package com.dzung.torre.genome.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private String professionalHeadline;
	private String completion;
	private boolean showPhone;
	private String created;
	private boolean verified;
	private Flags flags;
	private long weight;
	private String locale;
	private int subjectId;
	private String picture;
	private boolean hasEmail;
	private boolean isTest;
	private String name;
	private List<Links> links;
	private Location location;
	private String theme;
	private String id;
	private String pictureThumbnail;
	private boolean claimant;
	private String summaryOfBio;
	private String weightGraph;
	private String publicId;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class Flags {

		private boolean benefits;
		private boolean canary;
		private boolean enlauSource;
		private boolean fake;
		private boolean featureDiscovery;
		private boolean getSignaledBenefitsViewed;
		private boolean firstSignalSent;
		private boolean signalsOnboardingCompleted;
		private boolean importingLinkedin;
		private boolean onBoarded;
		private boolean remoter;
		private boolean signalsFeatureDiscovery;
		private boolean importingLinkedinRecommendations;
		private boolean contactsImported;
		private boolean appContactsImported;
		private boolean genomeCompletionAcknowledged;

		@Override
		public String toString() {
			return "Flags [benefits=" + benefits + ", canary=" + canary + ", enlauSource=" + enlauSource + ", fake="
					+ fake + ", featureDiscovery=" + featureDiscovery + ", getSignaledBenefitsViewed="
					+ getSignaledBenefitsViewed + ", firstSignalSent=" + firstSignalSent
					+ ", signalsOnboardingCompleted=" + signalsOnboardingCompleted + ", importingLinkedin="
					+ importingLinkedin + ", onBoarded=" + onBoarded + ", remoter=" + remoter
					+ ", signalsFeatureDiscovery=" + signalsFeatureDiscovery + ", importingLinkedinRecommendations="
					+ importingLinkedinRecommendations + ", contactsImported=" + contactsImported
					+ ", appContactsImported=" + appContactsImported + ", genomeCompletionAcknowledged="
					+ genomeCompletionAcknowledged + "]";
		}

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Links {

		private String id;
		private String name;
		private String address;

		@Override
		public String toString() {
			return "Links [id=" + id + ", name=" + name + ", address=" + address + "]";
		}

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	private class Location {

		private String name;
		private String shortName;
		private String country;
		private String latitude;
		private String longitude;
		private String timezone;
		private String timezoneOffSet;

		@Override
		public String toString() {
			return "Location [name=" + name + ", shortName=" + shortName + ", country=" + country + ", latitude="
					+ latitude + ", longitude=" + longitude + ", timezone=" + timezone + ", timezoneOffSet="
					+ timezoneOffSet + "]";
		}

	}
}
