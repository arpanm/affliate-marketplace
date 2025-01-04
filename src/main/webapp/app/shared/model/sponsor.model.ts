import dayjs from 'dayjs';

export interface ISponsor {
  id?: number;
  sponsorName?: string | null;
  sponsorDescription?: string | null;
  sponsorBanner1Url?: string | null;
  sponsorBanner2Url?: string | null;
  sponsorBanner3Url?: string | null;
  sponsorExternalUrl?: string | null;
  sponsorLogoUrl?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<ISponsor> = {
  isActive: false,
};
