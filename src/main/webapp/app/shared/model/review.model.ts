import dayjs from 'dayjs';
import { IVideoUser } from 'app/shared/model/video-user.model';
import { IVideoPost } from 'app/shared/model/video-post.model';
import { ReportType } from 'app/shared/model/enumerations/report-type.model';

export interface IReview {
  id?: number;
  isLiked?: boolean | null;
  isSkipped?: boolean | null;
  isDisliked?: boolean | null;
  isWatched?: boolean | null;
  isFullyWatched?: boolean | null;
  isReported?: boolean | null;
  reportType?: keyof typeof ReportType | null;
  rating?: number | null;
  comment?: string | null;
  reportReason?: string | null;
  isBlocked?: boolean | null;
  isModerated?: boolean | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  reviewer?: IVideoUser | null;
  post?: IVideoPost | null;
}

export const defaultValue: Readonly<IReview> = {
  isLiked: false,
  isSkipped: false,
  isDisliked: false,
  isWatched: false,
  isFullyWatched: false,
  isReported: false,
  isBlocked: false,
  isModerated: false,
  isActive: false,
};
